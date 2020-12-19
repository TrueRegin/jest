package fun.nothaving.jest.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.engine.constants.CustomEffects;
import fun.nothaving.jest.engine.constants.Generic;
import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.structs.JestEffectStats;
import fun.nothaving.jest.utils.entity.EntTeleport;
import fun.nothaving.jest.utils.event_listener.ListenerWithInfo;

public class PlayerListener extends ListenerWithInfo {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        StateManager.addPlayer(player);
        if (StateManager.getCustomEffectsEnabled()) {
            EntTeleport.ScatterFromSpawn(player);
        }

    }

    // When a player moves
    @EventHandler
    public void movementCheck(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PlayerState state = StateManager.getState(player);

        tickPlayer(player, state);
    }

    public void tickPlayer(Player player, PlayerState state) {
        if (state.hasMaxSwimFatigue()) {
            CustomEffects.SWIM_BLINDNESS().apply(player,
                    new JestEffectStats(Generic.SWIM_BLINDNESS_DURATION, CustomEffects.SWIM_BLINDNESS_MAX_LEVEL));
        }
        if (state.hasMaxRunFatigue()) {
            CustomEffects.SPRINT_NAUSEA().apply(player,
                    new JestEffectStats(Generic.SPRINT_NAUSEA_DURATION, CustomEffects.SPRINT_NAUSEA_MAX_LEVEL));
        }
    }

    public void getInfo() {
        Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "   togglePlayerEffects = "
                + StateManager.getCustomEffectsEnabled());
    }

}