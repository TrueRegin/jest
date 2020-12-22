package fun.nothaving.jest.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import fun.nothaving.jest.engine.StateManager;
import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.utils.event_listener.ListenerWithInfo;

public class EntityListener extends ListenerWithInfo {

    /**
     * Will be reworked in the future to incorporate the noheal state for all entities.
     */
    @EventHandler
    public void onEntityRegen(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player) {
            Player p = (Player) (event.getEntity());
            PlayerState state = StateManager.getState(p);

            if(!state.canRegen()) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event) {
        Entity entity_taking_damage = event.getEntity();
        if(entity_taking_damage instanceof Player) {
            Player player = (Player) entity_taking_damage;
            PlayerState state = StateManager.getState(player);
            double damage = event.getDamage();
            EntityType type = event.getDamager().getType();
            double pain_multiplier = state.getPainScalarForEntity(type);
            event.setDamage(damage * pain_multiplier);
        }

    }

    public void getInfo() {}
}
