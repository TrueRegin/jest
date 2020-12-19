package fun.nothaving.jest.engine.core.state;

public class EntityState {
    protected boolean canRegen = true;

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
}
