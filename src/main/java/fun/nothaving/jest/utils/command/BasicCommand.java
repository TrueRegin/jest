package fun.nothaving.jest.utils.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fun.nothaving.jest.structs.CommandProperties;

abstract class BasicCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return onCommand(new CommandProperties(sender, command, label, args));
    }

    public abstract boolean onCommand(CommandProperties props);
}
