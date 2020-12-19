package fun.nothaving.jest.utils.effect;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.engine.constants.CustomEffects;
import fun.nothaving.jest.engine.constants.Generic;
import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.quirks.NoRegenQuirk;
import fun.nothaving.jest.structs.JestEffectStats;
import fun.nothaving.jest.utils.effect.quirks.JestQuirk;
import fun.nothaving.jest.utils.effect.quirks.PotionQuirk;
import fun.nothaving.jest.utils.effect.quirks.StateQuirk;

/**
 * In the future debuffs and the effects they apply will be stored in a config,
 * which will make it easier to add new debuffs and give more freedom to anyone
 * using this plugin on a server.
 */

public class JestEffect {

    public JestQuirk[] quirks;

    public JestEffect(JestQuirk... quirks) {
        this.quirks = quirks;
    }

    public boolean apply(Player player, JestEffectStats stats) {
        /**
         * Apply the custom effect in the players effect_list
         */
        PlayerState state = StateManager.getState(player);
        state.addEffect(this, stats);
        for(JestQuirk quirk : quirks) {
            if(quirk instanceof PotionQuirk) {
                PotionQuirk pq = (PotionQuirk) quirk;
                pq.apply(player, stats);
            } else if(quirk instanceof StateQuirk) {
                StateQuirk sq = (StateQuirk) quirk;
                sq.apply(state);
            }
        }
        return true;
    }

    public boolean tick(Player player) {
        PlayerState state = StateManager.getState(player);
        for(JestQuirk quirk : quirks) {
            if(quirk instanceof StateQuirk) {
                StateQuirk sq = (StateQuirk) quirk;
                sq.tick(state);
            }
        }
        return true;
    }

    public boolean remove(Player player) {
        PlayerState state = StateManager.getState(player);

        for(JestQuirk quirk : quirks) {
            if(quirk instanceof StateQuirk) {
                StateQuirk sq = (StateQuirk) quirk;
                sq.remove(state);
            }
        }
        return true;
    }

    /**
     * A method that takes in a custom effect and applies it to a player
     * CustomStatusEffect which will be renamed to CustomEffect will be a
     * class in the "structs" folder, and this class will be renamed to
     * StatusEffectManager.
     */
    // public static boolean ApplyStatusEffect(CustomStatusEffect effect) {}

    public static boolean ApplySprintNausea(Player player) {
        if(!StateManager.getCustomEffectsEnabled()) { return false; }
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Generic.SPRINT_NAUSEA_DURATION, CustomEffects.SWIM_BLINDNESS_MAX_LEVEL));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Generic.SPRINT_NAUSEA_DURATION, 1));
        return true;
    }

    public static boolean ApplySwimBlindness(Player player) {
        if(!StateManager.getCustomEffectsEnabled()) { return false; }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Generic.SWIM_BLINDNESS_DURATION, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Generic.SWIM_BLINDNESS_DURATION, CustomEffects.SWIM_BLINDNESS_MAX_LEVEL));
        return true;
    }
}
