package fun.nothaving.jest.engine.constants;

import org.bukkit.ChatColor;

/**
 * All errors should use proper punctuation. That means they should end in . ! or ?
 */
public class Errors {
    /**
     * Errors for commands
     */
    public static final String INVALID_ARGS_COUNT = ChatColor.RED + "Invalid number of arguments entered for this command. Missing arguments?";
    public static final String MALFORMED_ARGS = ChatColor.RED + "1 or more invalid arguments were entered. Did you enter an argument with invalid data?";
    public static final String INVALID_USERNAME = ChatColor.RED + "An invalid username was used in your command! Did you misscapitalize a letter or enter the wrong character?";
    
    /**
     * Custom Effect Errors
     */
    public static final String INVALID_EFFECT = ChatColor.RED + "Invalid effect, no effect applied.";

    /**
     * Internal Errors
     */
    public static final String DATA_ERROR_INTERNAL = "Invalid data entered!";
    public static final String INVALID_PARAMETER_INTERNAL = "Invalid parameters entered for this function. Did you input a value that does not meet the functions constraints?";
    public static final String NULL_PLAYERSTATE_INTERNAL = "Null PlayerState detected! This should never occur. EVER!";
}
