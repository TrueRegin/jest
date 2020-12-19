package fun.nothaving.jest.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
    
    public void getInfo() {}
}