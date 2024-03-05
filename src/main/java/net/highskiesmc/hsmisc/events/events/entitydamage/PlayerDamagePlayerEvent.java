package net.highskiesmc.hsmisc.events.events.entitydamage;

import org.bukkit.entity.Player;

public class PlayerDamagePlayerEvent extends AttackEvent<Player, Player> {
    private boolean cancel;

    public PlayerDamagePlayerEvent(double modifier, Player attacker, Player victim) {
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
