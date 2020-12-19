package fun.nothaving.jest.commands;

import org.bukkit.entity.Player;

import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.engine.constants.Errors;
import fun.nothaving.jest.engine.constants.Generic;
import fun.nothaving.jest.engine.constants.Success;
import fun.nothaving.jest.engine.core.math.MathUtils;
import fun.nothaving.jest.listeners.CreatureListener;
import fun.nothaving.jest.structs.CommandProperties;
import fun.nothaving.jest.utils.command.PlayerCommand;

public class SetMobDifficultyCommand extends PlayerCommand {

    public boolean onCommand(CommandProperties props) {
        if (props.sender instanceof Player) {
            Player player = (Player) props.sender;
            if(props.args.length == 1) {
                try {
                    int new_diff = MathUtils.clamp(Generic.MIN_DIFFICULTY, Generic.MAX_DIFFICULTY, Integer.parseInt(props.args[0]));
                    StateManager.setCustomDifficulty(new_diff);
                    player.sendMessage(Success.DIFFICULTY_CHANGED(Generic.DIFFICULTIES.getByValue(new_diff)));
                    return true;
                } 
                
                catch(NumberFormatException e) {
                    Integer difficulty = Generic.DIFFICULTIES.getByLabel(props.args[0]);
                    if(difficulty == null) {
                        player.sendMessage(Errors.MALFORMED_ARGS);
                        return false;
                    } else {
                        StateManager.setCustomDifficulty(difficulty);
                        player.sendMessage(Success.DIFFICULTY_CHANGED(props.args[0]));
                    }
                }
            } else if(props.args.length == 0) {
                StateManager.toggleCustomDifficulty();
                player.sendMessage(Success.STATE_CHANGED);
                return true;
            } else {
                player.sendMessage(Errors.INVALID_ARGS_COUNT);
                return false;
            }
        }
        
        return false;
    }
}
