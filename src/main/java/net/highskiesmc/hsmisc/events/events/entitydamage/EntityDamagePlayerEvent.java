package net.highskiesmc.hsmisc.events.events.entitydamage;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class EntityDamagePlayerEvent extends AttackEvent<Entity, Player> {
    private boolean cancel;
    public EntityDamagePlayerEvent(double modifier, Entity attacker, Player victim) {
        super(modifier, attacker, victim);
        this.cancel = false;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
