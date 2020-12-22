package fun.nothaving.jest.engine;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fun.nothaving.jest.engine.constants.Errors;
import fun.nothaving.jest.engine.constants.Generic;
import fun.nothaving.jest.engine.core.math.MathUtils;
import fun.nothaving.jest.engine.core.state.PlayerState;
import net.md_5.bungee.api.ChatColor;

/**
 * The StateManager is in charge ofew managing all states in the game, you are
 * able to access most generic state management settings in it.
 * 
 * More complex features like effects have their own state manager.
 * 
 * Note: This state manager is only intended for general SERVER WIDE states.
 */
public class StateManager {
    private static HashMap<Player, PlayerState> STATES;
    private static boolean initialized = false;
    private static boolean custom_status_effects_enabled = true;
    private static boolean custom_difficulty_enabled = true;
    private static int custom_difficulty = Generic.MIN_DIFFICULTY;

    /**
     * ! Initialization
     */

    public static void init() {
        initialized = true;
        STATES = new HashMap<Player, PlayerState>();
    }

    /**
     * Is executed once every single in game tick.
     */
    public static void tick() {
        for(Player player : STATES.keySet()) {
            PlayerState state = STATES.get(player);
            state.updateSprintFatigue();
            state.updateSwimFatigue();
            state.setSprinting(player.isSprinting() && !player.isSwimming());
            state.setSwimming(player.isSwimming());
            state.tick(player);
        }
    }

    /**
     * Setters
     */

    public static void addPlayer(Player player) {
        if (!STATES.containsKey(player) && STATES.get(player) == null) {
            System.out.println("Added PLAYER!");
            STATES.put(player, new PlayerState(Generic.DFT_MAX_RUN_FATIGUE, Generic.DFT_MAX_SWIM_FATIGUE));
        }
    }

    public static void addPlayer(UUID player_uuid) {
        Player player = Bukkit.getServer().getPlayer(player_uuid);
        addPlayer(player);
    }

    public static void toggleCustomStatusEffects() {
        custom_status_effects_enabled = !custom_status_effects_enabled;
        if (custom_status_effects_enabled) {
            Bukkit.broadcastMessage(
                    ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Custom Player Effects are now ENABLED");
        } else {
            Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Custom Player Effects are now DISABLED");
        }
    }

    public static void toggleCustomDifficulty() {
        custom_difficulty_enabled = !custom_difficulty_enabled;
        if (custom_difficulty_enabled) {
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Custom Mob Difficulty is now ENABLED.");
        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Custom Mob Difficulty DISABLED.");
        }
    }

    public static void setCustomDifficulty(int difficulty) {
        difficulty = MathUtils.clamp(1, Generic.MAX_DIFFICULTY, difficulty);
        custom_difficulty = difficulty;
    }

    /**
     * Getters
     */

    public static PlayerState getState(Player player) {
        PlayerState state = STATES.get(player);
        if (state == null)
            throw new NullPointerException(Errors.NULL_PLAYERSTATE_INTERNAL + "\nPlayer: " + player.toString());
        return state;
    }

    public static PlayerState getState(UUID player_uuid) {
        return getState(Bukkit.getServer().getPlayer(player_uuid));
    }

    public static boolean getCustomEffectsEnabled() {
        return custom_status_effects_enabled;
    }

    public static boolean getCustomDifficultyEnabled() {
        return custom_difficulty_enabled;
    }

    public static int getCustomDifficulty() {
        return custom_difficulty;
    }
}
