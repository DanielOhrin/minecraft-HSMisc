package net.highskiesmc.hsmisc.events.events.entitydamage;

import net.highskiesmc.hsmisc.events.events.ModifiableEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public abstract class AttackEvent<A extends Entity, V extends Entity> extends ModifiableEvent implements Cancellable {
    private final A attacker;
    private final V victim;
    public AttackEvent(double modifier, A attacker, V victim) {
        super(modifier);
        this.attacker = attacker;
        this.victim = victim;
    }

    public A getAttacker() {
        return this.attacker;
    }

    public V getVictim() {
        return this.victim;
    }
}
