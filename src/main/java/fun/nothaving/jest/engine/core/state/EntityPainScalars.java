package fun.nothaving.jest.engine.core.state;

import java.util.HashMap;

import org.bukkit.entity.EntityType;

import fun.nothaving.jest.engine.core.math.MathUtils;

public class EntityPainScalars {
    private HashMap<EntityType, Double> damageScalars;

    public EntityPainScalars() {
        damageScalars = new HashMap<EntityType, Double>();
    }

    /**
     * Setters
     */

    public void initDamageScalarForEntity(EntityType type) {
        damageScalars.put(type, 1.0);
    }

    public void increaseDamageScalarForEntity(EntityType type, double amount) {
        double damageScalar = getDamageScalarForEntity(type);
        double newDamageScalar = MathUtils.clamp(0, Double.MAX_VALUE, damageScalar + amount);
        damageScalars.put(type, newDamageScalar);
    }

    public void decreaseDamageScalarForEntity(EntityType type, double amount) {
        increaseDamageScalarForEntity(type, -amount);
    }

    /**
     * Getters
     */

    public double getDamageScalarForEntity(EntityType type) {
        Double damageScalar = damageScalars.get(type);
        return damageScalar == null ? 1 : damageScalar.doubleValue();
    }
}
