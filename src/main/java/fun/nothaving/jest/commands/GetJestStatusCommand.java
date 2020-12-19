package fun.nothaving.jest.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import fun.nothaving.jest.listeners.CreatureListener;
import fun.nothaving.jest.listeners.PlayerListener;
import fun.nothaving.jest.structs.CommandProperties;
import fun.nothaving.jest.utils.command.PlayerCommand;

public class GetJestStatusCommand extends PlayerCommand {
    private CreatureListener c_listener;
    private PlayerListener p_listener;

   public GetJestStatusCommand(PlayerListener p_listener, CreatureListener c_listener) {
       this.p_listener = p_listener;
       this.c_listener = c_listener;
   } 

   public boolean onCommand(CommandProperties props) {
        props.sender.sendMessage(ChatColor.BLUE + "" +ChatColor.BOLD +"----------------------");
        props.sender.sendMessage(ChatColor.BLUE + "" +ChatColor.BOLD +"[Jest Current Status]");
        p_listener.getInfo();
        c_listener.getInfo();
        props.sender.sendMessage(ChatColor.BLUE + "" +ChatColor.BOLD +"----------------------");
        return true;
   }
}
