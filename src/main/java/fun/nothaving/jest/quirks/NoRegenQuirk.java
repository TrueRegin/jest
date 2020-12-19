package fun.nothaving.jest.quirks;

import org.bukkit.Bukkit;

import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.utils.effect.quirks.StateQuirk;

public class NoRegenQuirk extends StateQuirk {
    public void apply(PlayerState state) {
        state.disableRegeneration();
    }

    public void tick(PlayerState state) {
    }

    public void remove(PlayerState state) {
        state.enableRegeneration();
    }
}
