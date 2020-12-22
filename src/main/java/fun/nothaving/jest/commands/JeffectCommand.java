package fun.nothaving.jest.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.engine.constants.CustomEffects;
import fun.nothaving.jest.engine.constants.Errors;
import fun.nothaving.jest.engine.constants.Success;
import fun.nothaving.jest.engine.core.math.MathUtils;
import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.structs.CommandProperties;
import fun.nothaving.jest.structs.JestEffectStats;
import fun.nothaving.jest.utils.command.PlayerCommand;

public class JeffectCommand extends PlayerCommand {
    public boolean onCommand(CommandProperties props) {
        String effect;
        boolean remove_effect = false;
        boolean effect_applied = false;

        // Checking to see whether we are giving or removing an effect from the player.
        // (!) Slightly difficult to read
        if (props.args.length >= 1) {
            String give_or_remove_effect = props.args[0];
            if (give_or_remove_effect.equalsIgnoreCase("remove")) {
                remove_effect = true;
            } else if (!give_or_remove_effect.equalsIgnoreCase("give")) {
                props.sender.sendMessage(Errors.INVALID_ARG_VALUES);
                return true;
            }
        }

        if (!remove_effect) {
            int duration = 0;
            byte intensity = 0;

            // Mode 1 of the function, the effect is applied to the command sender.
            if (props.args.length == 4) {
                try {
                    effect = props.args[1].toLowerCase();
                    duration = Integer.parseInt(props.args[2]) * 1000; // Duration in seconds
                    intensity = Byte.parseByte(props.args[3]);

                    effect_applied = applyEffectToPlayer(remove_effect, effect, (Player) (props.sender), duration,
                            intensity);
                    if (!effect_applied) {
                        props.sender.sendMessage(Errors.INVALID_EFFECT);
                        return true;
                    }
                    return true;
                } catch (NumberFormatException e) {
                    props.sender.sendMessage(Errors.INVALID_ARG_VALUES);
                    return true;
                }
            }

            // Mode 2 of the function, the effect is applied to some online player or an
            // error is sent back.
            else if (props.args.length == 5) {
                try {
                    Player target = Bukkit.getPlayer(props.args[1]);
                    effect = props.args[2].toLowerCase();
                    duration = Integer.parseInt(props.args[3]) * 1000; // Duration in seconds
                    intensity = Byte.parseByte(props.args[4]);

                    effect_applied = applyEffectToPlayer(remove_effect, effect, target, duration, intensity);
                    if (!effect_applied) {
                        props.sender.sendMessage(Errors.INVALID_EFFECT);
                        return true;
                    }
                    return true;
                } catch (NumberFormatException e) {
                    props.sender.sendMessage(Errors.INVALID_ARG_VALUES);
                    return true;
                } catch (NullPointerException e) {
                    props.sender.sendMessage(Errors.INVALID_USERNAME);
                    return true;
                }
            }
        } else {            
            if(props.args.length == 1) {
                StateManager.getState((Player) props.sender).removeAllEffects();
                props.sender.sendMessage(Success.ALL_EFFECTS_REMOVED_MESSAGE());
                return true;
            }
            else if(props.args.length == 2) {
                try {
                    Player target = Bukkit.getPlayer(props.args[1]);
                    StateManager.getState(target).removeAllEffects();
                    target.sendMessage(Success.ALL_EFFECTS_REMOVED_MESSAGE());
                    return true;
                } catch(Error e) {
                    props.sender.sendMessage(Errors.INVALID_USERNAME);
                    return true;
                }
            }
            else if(props.args.length == 3) {
                try {
                    Player target = Bukkit.getPlayer(props.args[1]);
                    String effect_name = props.args[2];

                    effect_applied = applyEffectToPlayer(remove_effect, effect_name, target, 0, (byte) 0);
                    if(!effect_applied) {
                        props.sender.sendMessage(Errors.INVALID_EFFECT);
                        return true;
                    }
                    return true;
                } catch(Error e) {
                    props.sender.sendMessage(Errors.INVALID_USERNAME);
                    return true;
                }
            }
        }

        props.sender.sendMessage(Errors.INVALID_ARGS_COUNT);
        return true;
    }

    private boolean applyEffectToPlayer(boolean remove_effect, String effect_name, Player target, int duration,
            byte intensity) {
        if (remove_effect == true) {
            duration = 0;
        } else {
            duration = MathUtils.clamp(0, Integer.MAX_VALUE, duration);
            intensity = MathUtils.clamp((byte) 0, Byte.MAX_VALUE, intensity);
        }

        switch (effect_name) {
            case "bleeding":
                CustomEffects.BLEEDING().apply(target, new JestEffectStats(duration, intensity));
                break;
            case "sprint_nausea":
                CustomEffects.SPRINT_NAUSEA().apply(target, new JestEffectStats(duration, intensity));
                break;
            case "swim_blindness":
                CustomEffects.SWIM_BLINDNESS().apply(target, new JestEffectStats(duration, intensity));
                break;
            case "wither_curse":
                CustomEffects.WITHER_CURSE().apply(target, new JestEffectStats(duration, intensity));
                break;
            default:
                return false;
        }

        if (remove_effect) {
            target.sendMessage(Success.EFFECT_REMOVED_MESSAGE(effect_name));
            return true;
        }
        target.sendMessage(Success.EFFECT_APPLIED_MESSAGE(effect_name, duration, intensity));
        return true;
    }
}
