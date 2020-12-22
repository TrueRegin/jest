package fun.nothaving.jest.engine.constants;

import org.bukkit.ChatColor;

public class Success {
    public static final String STATE_CHANGED = "State successfully changed!";
    public static final String DIFFICULTY_CHANGED(String difficulty) {
        return "Difficulty successfully changed to " + difficulty + "!";
    }

    public static final String EFFECT_APPLIED_MESSAGE(String effect_name, int duration_milli, byte intensity) {
        return ChatColor.GREEN + "You have gained the effect " + effect_name + " " + intensity + " for " + (duration_milli / 1000) + " seconds.";
    }

    public static final String EFFECT_REMOVED_MESSAGE(String effect_name) {
        return ChatColor.LIGHT_PURPLE + "The effect " + effect_name + " has been removed from you.";
    }

    public static final String ALL_EFFECTS_REMOVED_MESSAGE() {
        return ChatColor.LIGHT_PURPLE + "Successfully cleared all Jest custom effects from you.";
    }


}
