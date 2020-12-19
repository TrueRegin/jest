package fun.nothaving.jest.utils.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fun.nothaving.jest.structs.CommandProperties;

public abstract class PlayerCommand extends BasicCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            return onCommand(new CommandProperties(sender, command, label, args));
        }
        return false;
    }

    public abstract boolean onCommand(CommandProperties props);
}
