package fun.nothaving.jest.quirks;

import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.utils.effect.quirks.StateQuirk;

public class StrongQuirk extends StateQuirk {
    public void apply(PlayerState state) {
        state.incMaxRunFatigue(300);
        state.incMaxSwimFatigue(300);
    }

    public void tick(PlayerState state) {

    }

    public void remove(PlayerState state) {
        state.incMaxRunFatigue(-300);
        state.incMaxSwimFatigue(-300);
    }
}
