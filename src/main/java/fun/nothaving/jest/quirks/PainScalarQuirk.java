package fun.nothaving.jest.quirks;

import org.bukkit.entity.EntityType;

import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.utils.effect.quirks.StateQuirk;

public class PainScalarQuirk extends StateQuirk {
    private EntityType type;
    private double pain_scalar;

    public PainScalarQuirk(EntityType type, double pain_scalar) {
        this.type = type;
        this.pain_scalar = pain_scalar;
    }

    @Override
    public void apply(PlayerState state) {
        state.incPainScalarFromEntity(type, pain_scalar);
    }

    @Override
    public void tick(PlayerState state) {}

    @Override
    public void remove(PlayerState state) {
        state.decPainScalarFromEntity(type, pain_scalar);

    }
}
