package net.highskiesmc.hsmisc.events.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class ModifiableEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    protected double modifier;
    public ModifiableEvent(double modifier) {
        this.modifier = modifier;
    }

    /**
     *
     * @return Current modifier
     */
    public double getModifier() {
        return modifier;
    }

    /**
     * Adds the param onto the event's modifier
     * @param modifier Modifier to add
     */
    public void modifyBy(double modifier) {
        this.modifier += modifier;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }
}
