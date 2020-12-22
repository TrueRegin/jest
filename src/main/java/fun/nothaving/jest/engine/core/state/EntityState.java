package fun.nothaving.jest.engine.core.state;

import org.bukkit.entity.EntityType;

public class EntityState {
    protected boolean canRegen = true;
    protected EntityPainScalars painScalars;

    public EntityState() {
        this.painScalars = new EntityPainScalars();
    }

    // Getters
    public boolean canRegen() {
        return this.canRegen;
    }

    // Setters
    public void enableRegeneration() {
        this.canRegen = true;
    }
    public void disableRegeneration() {
        this.canRegen = false;
    }

    public void incPainScalarFromEntity(EntityType type, double amount) {
        painScalars.increaseDamageScalarForEntity(type, amount);
    }

    public void decPainScalarFromEntity(EntityType type, double amount) {
        painScalars.decreaseDamageScalarForEntity(type, amount);
    }

    public double getPainScalarForEntity(EntityType type) {
        return painScalars.getDamageScalarForEntity(type);
    }
}
