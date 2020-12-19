package fun.nothaving.jest.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fun.nothaving.jest.engine.constants.CustomEffects;
import fun.nothaving.jest.engine.constants.Errors;
import fun.nothaving.jest.structs.CommandProperties;
import fun.nothaving.jest.structs.JestEffectStats;
import fun.nothaving.jest.utils.command.PlayerCommand;

public class JeffectCommand extends PlayerCommand {
    public boolean onCommand(CommandProperties props) {
        if (props.args.length == 3) {
            try {
                String effect = props.args[0].toLowerCase();
                int duration = Integer.parseInt(props.args[1]) * 1000; // Duration in seconds
                byte intensity = Byte.parseByte(props.args[2]);

                return applyEffectToPlayer(effect, (Player) (props.sender), duration, intensity);
            } catch (NumberFormatException e) {
                props.sender.sendMessage(Errors.MALFORMED_ARGS);
                return false;
            }
        }

        else if (props.args.length == 4) {
            try {
                String effect = props.args[0].toLowerCase();
                Player target = Bukkit.getPlayer(props.args[2]);
                int duration = Integer.parseInt(props.args[2]) * 1000; // Duration in seconds
                byte intensity = Byte.parseByte(props.args[3]);

                return applyEffectToPlayer(effect, target, duration, intensity);
            } catch (NumberFormatException e) {
                props.sender.sendMessage(Errors.MALFORMED_ARGS);
                return false;
            } catch(Error e) {
                props.sender.sendMessage(Errors.INVALID_USERNAME);
                return false;
            }
        }

        props.sender.sendMessage(Errors.INVALID_ARGS_COUNT);
        return false;
    }

    private boolean applyEffectToPlayer(String effect, Player target, int duration, byte intensity) {
        switch (effect) {
            case "bleeding":
                CustomEffects.BLEEDING().apply(target, new JestEffectStats(duration, intensity));
                break;
            case "sprint_nausea":
                CustomEffects.SPRINT_NAUSEA().apply(target, new JestEffectStats(duration, intensity));
                break;
            case "swim_blindness":
                CustomEffects.SWIM_BLINDNESS().apply(target, new JestEffectStats(duration, intensity));
                break;
            default:
                target.sendMessage(Errors.INVALID_EFFECT);
                return false;
        }
        return true;
    }
}
