package fun.nothaving.jest.structs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandProperties {
    public CommandSender sender;
    public Command command;
    public String name;
    public String[] args;

    public CommandProperties(CommandSender sender, Command command, String name, String[] args) {
        this.sender = sender;
        this.command = command;
        this.name = name;
        this.args = args;
    }
}
