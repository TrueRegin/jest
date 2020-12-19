package fun.nothaving.jest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fun.nothaving.jest.commands.JeffectCommand;
import fun.nothaving.jest.commands.GetJestStatusCommand;
import fun.nothaving.jest.commands.SetMobDifficultyCommand;
import fun.nothaving.jest.engine.Engine;
import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.engine.TickScheduler;
import fun.nothaving.jest.listeners.CreatureListener;
import fun.nothaving.jest.listeners.EntityListener;
import fun.nothaving.jest.listeners.PlayerListener;
import fun.nothaving.jest.utils.effect.JestEffect;

public class Main extends JavaPlugin {

    CreatureListener c_listener;
    PlayerListener p_listener;
    EntityListener e_listener;
    TickScheduler scheduler;

    /**
     * Registers all custom event listeners
     */
    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(p_listener, this);
        pm.registerEvents(c_listener, this);
        pm.registerEvents(e_listener, this);

        getServer().getConsoleSender().sendMessage(JestEffect.class.getTypeName());
    }

    /**
     * Registers all custom commands with Spigot
     */
    private void registerCommands() {
        getCommand("setMobDifficulty").setExecutor(new SetMobDifficultyCommand());
        getCommand("getJestStatus").setExecutor(new GetJestStatusCommand(p_listener, c_listener));
        getCommand("jeffect").setExecutor(new JeffectCommand());
    }

    public void loadOnlinePlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            StateManager.addPlayer(player);
        }
    }

    @Override
    public void onEnable() {
        init();
        Engine.init();
        loadOnlinePlayers();
        scheduler.runTaskTimer(this, 0, 1);
        registerEvents();
        registerCommands();
        getLogger().info(ChatColor.LIGHT_PURPLE + "Jest has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.DARK_RED + "Jest has been disabled.");
        // Save all state into a database or file.
        scheduler.cancel();
    }

    private void init() {
        c_listener = new CreatureListener();
        p_listener = new PlayerListener();
        e_listener = new EntityListener();
        scheduler = new TickScheduler();
    }
}