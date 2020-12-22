package fun.nothaving.jest.commands.tab_completers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fun.nothaving.jest.engine.constants.CustomEffects;
import fun.nothaving.jest.engine.constants.Errors;

public class JeffectTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> ONLINE_USERS_ARG = getOnlinePlayerUsernames();
        boolean self_mode = true;
        boolean remove_effect_mode = false;

        if (args.length >= 1) {
            String give_or_remove_effect = args[0];
            if (give_or_remove_effect.equalsIgnoreCase("remove")) {
                remove_effect_mode = true;
            }
        }


        // Detect what mode the command is being run in.
        if(args.length >= 2) {
            try {
                Integer.parseInt(args[1]);
            } catch(NumberFormatException e) {
                self_mode = false;
            }
        }

        if(args.length == 1) {
            return GIVE_OR_REMOVE_ARG;
        }
        
        if(!remove_effect_mode) {
    
            if(args.length == 2 && !self_mode) {
                return ONLINE_USERS_ARG;
            }
    
            else if(args.length == 2 && self_mode || args.length == 3 && !self_mode) {
                return new ArrayList<String>(Arrays.asList(CustomEffects.EFFECTS_LIST));
            }
            
            else if(args.length == 3 && self_mode || args.length == 4 && !self_mode) {
                return DURATION_ARG;
            }
            else if(args.length == 4 && self_mode || args.length == 5 && !self_mode) {
                return INTENSITY_ARG;
            }
        } else {
            if(args.length == 2) {
                return ONLINE_USERS_ARG;
            }
            else if(args.length == 3) {
                return new ArrayList<String>(Arrays.asList(CustomEffects.EFFECTS_LIST));
            }
        }
        
        return new ArrayList<String>();
    }

    private static final ArrayList<String> GIVE_OR_REMOVE_ARG = new ArrayList<String>(Arrays.asList("give", "remove"));
    private static final ArrayList<String> DURATION_ARG = new ArrayList<String>();
    private static final ArrayList<String> INTENSITY_ARG = new ArrayList<String>();

    private ArrayList<String> getOnlinePlayerUsernames() {
        ArrayList<String> usernames = new ArrayList<String>();
        for(Player p : Bukkit.getOnlinePlayers()) {
            usernames.add(p.getName());
        }
        
        return usernames;
    }
}
