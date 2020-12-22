package fun.nothaving.jest.commands.tab_completers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fun.nothaving.jest.engine.constants.Generic;

public class SetMobDifficultyTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            ArrayList<String> difficulties = new ArrayList<String>();
            for(int i = Generic.MIN_DIFFICULTY; i <= Generic.MAX_DIFFICULTY; i++) {
                String difficulty_name = Generic.DIFFICULTIES.getByValue(i);
                difficulties.add(difficulty_name);
            }

            return difficulties;
        }
        else {
            return new ArrayList<String>();
        }
    }
}
