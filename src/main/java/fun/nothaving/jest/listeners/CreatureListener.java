package fun.nothaving.jest.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.engine.constants.Generic;
import fun.nothaving.jest.utils.entity.EntDifficulty;
import fun.nothaving.jest.utils.event_listener.ListenerWithInfo;

public class CreatureListener extends ListenerWithInfo {
    // ------------------------------------------------------------
    // Active listener methods
    // ------------------------------------------------------------

    // When a mob spawns
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (!StateManager.getCustomDifficultyEnabled())
            return;
        LivingEntity creature = event.getEntity();
        EntDifficulty.setMobDifficulty(creature);
    }

    public void getInfo() {
        boolean custom_difficulty_enabled = StateManager.getCustomDifficultyEnabled();
        String custom_difficulty = Generic.DIFFICULTIES.getByValue(StateManager.getCustomDifficulty());
        Bukkit.broadcastMessage(
                ChatColor.BLUE + "" + ChatColor.BOLD + "    setMobDifficulty = " + custom_difficulty_enabled);
        if (custom_difficulty_enabled) {
            switch (StateManager.getCustomDifficulty()) {
                case 1:
                    Bukkit.broadcastMessage(
                            ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "    Difficulty = " + custom_difficulty);
                    break;
                case 2:
                    Bukkit.broadcastMessage(
                            ChatColor.YELLOW + "" + ChatColor.BOLD + "    MobDifficultyLevel " + custom_difficulty);
                    break;
                case 3:
                    Bukkit.broadcastMessage(
                            ChatColor.RED + "" + ChatColor.BOLD + "    MobDifficultyLevel " + custom_difficulty);
                    break;
                default:
                    break;
            }
        }
    }
}