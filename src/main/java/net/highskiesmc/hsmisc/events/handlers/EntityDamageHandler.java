package net.highskiesmc.hsmisc.events.handlers;

import net.highskiesmc.hscore.highskies.HSListener;
import net.highskiesmc.hscore.highskies.HSPlugin;
import net.highskiesmc.hsmisc.events.events.entitydamage.AttackEvent;
import net.highskiesmc.hsmisc.events.events.entitydamage.EntityDamagePlayerEvent;
import net.highskiesmc.hsmisc.events.events.entitydamage.PlayerDamageEntityEvent;
import net.highskiesmc.hsmisc.events.events.entitydamage.PlayerDamagePlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageHandler extends HSListener {
    public EntityDamageHandler(HSPlugin main) {
        super(main);
    }

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
        Entity attacker = e.getDamager();
        Entity victim = e.getEntity();
        AttackEvent<?, ?> event = null;

        if (attacker instanceof Player a) {
            if (victim instanceof Player v) {
                event = new PlayerDamagePlayerEvent(1, a, v);
            } else {
                event = new PlayerDamageEntityEvent(1, a, victim);
            }
        } else {
            if (victim instanceof Player v) {
                event = new EntityDamagePlayerEvent(1, attacker, v);
            } else {
                return;
            }
        }

        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            e.setCancelled(true);
            return;
        }

        double dmg = e.getDamage();
        double newDmg = e.getDamage() * event.getModifier();
        System.out.println("Original Damage: " + dmg);
        System.out.println("Modifier: " + event.getModifier());
        System.out.println("New Damage: " + newDmg);
        e.setDamage(e.getDamage() * event.getModifier());
    }
}
