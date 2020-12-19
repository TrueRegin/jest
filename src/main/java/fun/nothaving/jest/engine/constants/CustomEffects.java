package fun.nothaving.jest.engine.constants;

import org.bukkit.potion.PotionEffectType;

import fun.nothaving.jest.quirks.NoRegenQuirk;
import fun.nothaving.jest.utils.effect.JestEffect;
import fun.nothaving.jest.utils.effect.quirks.PotionQuirk;

public class CustomEffects {
    public static final byte SPRINT_NAUSEA_MAX_LEVEL = 2;
    public static final byte SWIM_BLINDNESS_MAX_LEVEL = 3;

    public static void init() {
        BLEEDING = new JestEffect(new NoRegenQuirk(), new PotionQuirk(PotionEffectType.WITHER));
        SPRINT_NAUSEA = new JestEffect(new PotionQuirk(PotionEffectType.CONFUSION),
                new PotionQuirk(PotionEffectType.SLOW));
        SWIM_BLINDNESS = new JestEffect(new PotionQuirk(PotionEffectType.BLINDNESS),
                new PotionQuirk(PotionEffectType.SLOW));
    }

    private static JestEffect BLEEDING;
    private static JestEffect SWIM_BLINDNESS;
    private static JestEffect SPRINT_NAUSEA;

    public static JestEffect BLEEDING() {
        return BLEEDING;
    }

    public static JestEffect SWIM_BLINDNESS() {
        return SWIM_BLINDNESS;
    }

    public static JestEffect SPRINT_NAUSEA() {
        return SPRINT_NAUSEA;
    }
}
