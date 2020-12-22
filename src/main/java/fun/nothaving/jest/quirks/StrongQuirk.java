package fun.nothaving.jest.quirks;

import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.utils.effect.quirks.StateQuirk;

public class StrongQuirk extends StateQuirk {
    @Override
    public void apply(PlayerState state) {
        state.incMaxRunFatigue(10000);
        state.incMaxSwimFatigue(10000);
    }

    @Override
    public void tick(PlayerState state) {

    }

    @Override
    public void remove(PlayerState state) {
        state.decMaxRunFatigue(10000);
        state.decMaxSwimFatigue(10000);
    }
}
