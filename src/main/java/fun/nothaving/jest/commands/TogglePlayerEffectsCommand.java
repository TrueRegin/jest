package fun.nothaving.jest.commands;

import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.listeners.PlayerListener;
import fun.nothaving.jest.structs.CommandProperties;
import fun.nothaving.jest.utils.command.PlayerCommand;

public class TogglePlayerEffectsCommand extends PlayerCommand {
    public boolean onCommand(CommandProperties props) {
        StateManager.toggleCustomStatusEffects();
        return true;
    }
}
