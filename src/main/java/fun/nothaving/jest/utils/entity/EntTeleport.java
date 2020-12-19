package fun.nothaving.jest.utils.entity;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.nothaving.jest.engine.constants.Generic;

public class EntTeleport {
    public static void ScatterFromSpawn(Player player) {
        Random random = new Random();

		final int slowfall_duration = 125;
		
		if(!player.hasPlayedBefore()) {
            player.teleport(new Location(player.getWorld(), random.nextInt(Generic.SCATTER_X_MAX), Generic.SCATTER_HEIGHT, random.nextInt(Generic.SCATTER_Z_MAX)));
            
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, slowfall_duration, 20, true, false));
		}
    }
}
