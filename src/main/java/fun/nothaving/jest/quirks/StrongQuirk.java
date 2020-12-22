package fun.nothaving.jest.quirks;

import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.utils.effect.quirks.StateQuirk;

public class StrongQuirk extends StateQuirk {

    int runFatigueIncrease;
    int swimFatigueIncrease;

    public StrongQuirk() {
        this.runFatigueIncrease = 0;
        this.swimFatigueIncrease = 0;
    }

    @Override
    public void apply(PlayerState state) {
        this.runFatigueIncrease = state.incMaxRunFatigue(10000);
        this.swimFatigueIncrease = state.incMaxSwimFatigue(10000);
    }

    @Override
    public void tick(PlayerState state) {}

    @Override
    public void remove(PlayerState state) {
        state.decMaxRunFatigue(this.runFatigueIncrease);
        state.decMaxSwimFatigue(this.swimFatigueIncrease);
        this.runFatigueIncrease = 0;
        this.swimFatigueIncrease = 0;
    }
}
