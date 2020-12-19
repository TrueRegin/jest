package fun.nothaving.jest.engine.core.state;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fun.nothaving.jest.engine.constants.Generic;
import fun.nothaving.jest.engine.core.math.MathUtils;
import fun.nothaving.jest.structs.JestEffectStats;
import fun.nothaving.jest.utils.effect.JestEffect;

public class PlayerState extends EntityState {

    protected int max_sprint_fatigue = 0;
    protected int max_swim_fatigue = 0;
    protected double sprint_fatigue = 0;
    protected double swim_fatigue = 0;
    protected long prev_sprint_timestamp = -1;
    protected long prev_swim_timestamp = -1;

    protected boolean sprinting_last_tick;
    protected boolean swimming_last_tick;
    protected boolean currently_sprinting;
    protected boolean currently_swimming;

    protected Player player;
    protected HashMap<JestEffect, JestEffectStats> effects;

    public PlayerState(Player player, int max_run_fatigue, int max_swim_fatigue) {
        this.player = player;
        this.max_sprint_fatigue = max_run_fatigue;
        this.max_swim_fatigue = max_swim_fatigue;
        this.sprinting_last_tick = false;
        this.currently_sprinting = false;
        this.swimming_last_tick = false;
        this.currently_swimming = false;
        effects = new HashMap<JestEffect, JestEffectStats>();
    }

    public void tick() {
        // Go through all effects and tick them if they're tickable.
        // Effect.tick(player, this)
        for(JestEffect effect : effects.keySet()) {
            JestEffectStats effectStats = effects.get(effect);

            effectStats.updateDuration();
            if(effectStats.hasExpired()) {
                removeEffect(effect);
            } else {
                effect.tick(player);
            }
        }
    }

    /**
     * Effects
     */

    /**
     * Adds an effect to a player.
     * @param effect - The effect to apply to the player, they are staticly stored in "fun.nothaving.jest.engine.constants.CustomEffects.java"
     * @param stats - The duration and intensity of the potion to be applied
     */
    public void addEffect(JestEffect effect, JestEffectStats stats) {
        effects.put(effect, stats);
    }

    /**
     * Removes an applied effect from a player so it no longer takes effect on them.
     * @param effect
     */
    public void removeEffect(JestEffect effect) {
        effect.remove(player);
        effects.remove(effect);
    }


    /**
     * Getters
     */

    /** */
    public double getSwimFatigue() {
        return swim_fatigue;
    }
    
    public boolean hasMaxSwimFatigue() {
        return swim_fatigue == max_swim_fatigue;
    }

    public double getRunFatigue() {
        return sprint_fatigue;
    }

    public boolean hasMaxRunFatigue() {
        return sprint_fatigue == max_sprint_fatigue;
    }


    /**
     * Setters
     */

    public void incMaxRunFatigue(int amount) {
        int newMaxFatigue = MathUtils.clamp(Generic.MIN_RUN_FATIGUE, max_sprint_fatigue, max_sprint_fatigue + amount);
        this.sprint_fatigue = newMaxFatigue;
    }

    public void decMaxRunFatigue(int amount) {
        incMaxRunFatigue(-amount);
    }

    public void incMaxSwimFatigue(int amount) {
        int newMaxFatigue = MathUtils.clamp(Generic.MIN_SWIM_FATIGUE, max_swim_fatigue, max_swim_fatigue + amount);
        this.swim_fatigue = newMaxFatigue;
    }

    public void decMaxSwimFatigue(int amount) {
        incMaxRunFatigue(-amount);
    }

    /** */
    private void setSwimFatigue(double newSwimFatigue) {
        this.swim_fatigue = MathUtils.clamp(Generic.MIN_SWIM_FATIGUE, max_swim_fatigue, newSwimFatigue);
    }

    private void setRunFatigue(double newRunFatigue) {
        this.sprint_fatigue = MathUtils.clamp(Generic.MIN_RUN_FATIGUE, max_sprint_fatigue, newRunFatigue);
    }

    /**
     * Updates the current amount of swim fatigue a player has.
     * @param new_swim_timestamp
    */
    public void updateSwimFatigue() {
        long new_swim_timestamp = System.currentTimeMillis();
        if(this.prev_swim_timestamp != -1) {
            int fatigue_diff = (int) (MathUtils.clamp(0, max_swim_fatigue, new_swim_timestamp - prev_swim_timestamp));
            fatigue_diff = currently_swimming ? fatigue_diff : -fatigue_diff;
            setSwimFatigue(swim_fatigue + fatigue_diff);
        }
        this.prev_swim_timestamp = new_swim_timestamp;
    }

    /**
     * Updates the current sprint fatigue a player has.
     * @param new_sprint_timestamp
     */
    public void updateSprintFatigue() {
        long new_sprint_timestamp = System.currentTimeMillis();
        if(this.prev_sprint_timestamp != -1) {
            int fatigue_diff = (int) (MathUtils.clamp(0, max_sprint_fatigue, new_sprint_timestamp - prev_sprint_timestamp));
            fatigue_diff = currently_sprinting ? fatigue_diff : -fatigue_diff;
            setRunFatigue(sprint_fatigue + fatigue_diff);
        }
        this.prev_sprint_timestamp = new_sprint_timestamp;
    }

    public void setSprinting(boolean is_currently_sprinting) {
        this.sprinting_last_tick = currently_sprinting;
        this.currently_sprinting = is_currently_sprinting;
    }

    public void setSwimming(boolean is_currently_swimming) {
        this.swimming_last_tick = this.currently_swimming;
        this.currently_swimming = is_currently_swimming;
    }
}
