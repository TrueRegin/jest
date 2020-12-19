package fun.nothaving.jest.engine;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class TickScheduler extends BukkitRunnable {
    @Override
    public void run() {
        tick();
    }
    
    public void tick() {
        Engine.tick();
    }
}
