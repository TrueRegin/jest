package fun.nothaving.jest.utils.effect.quirks;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.nothaving.jest.structs.JestEffectStats;

public class PotionQuirk extends JestQuirk {
    private PotionEffectType type;

    public PotionQuirk(PotionEffectType type) {
        this.type = type;
    }

    /**
     * This method is run once when the effect is applied to a player
     */
    public void apply(Player player, JestEffectStats stats) {
        player.addPotionEffect(new PotionEffect(type, stats.getDuration() / 50, stats.getIntensity()));
    }
}
