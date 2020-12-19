package fun.nothaving.jest.utils.effect.quirks;

import fun.nothaving.jest.engine.core.state.PlayerState;

public abstract class StateQuirk extends JestQuirk {
    /**
     * This method applies buffs/debuffs that the StateQuirk adds to a players state
     */
    public abstract void apply(PlayerState state);

    /**
     * This method is run every tick the StateQuirk is applied to a player
    */
    public abstract void tick(PlayerState state);

    /**
     * This method reverts any state changes applied by the StateQuirk
     */
    public abstract void remove(PlayerState state);
}
