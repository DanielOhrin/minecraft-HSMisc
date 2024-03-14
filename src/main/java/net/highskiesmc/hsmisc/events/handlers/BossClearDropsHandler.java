package net.highskiesmc.hsmisc.events.handlers;

import net.highskiesmc.hscore.highskies.HSListener;
import net.highskiesmc.hscore.highskies.HSPlugin;
import net.splodgebox.elitebosses.events.BossDeathEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BossClearDropsHandler implements Listener {

    /**
     * This is a fix for EliteBosses not clearing equipment on death of boss
     *
     * @param event
     */
    @EventHandler
    public void onBossDeath(BossDeathEvent event) {
        LivingEntity livingEntity = (LivingEntity) event.getBoss();

        livingEntity.getEquipment().clear();
    }
}
