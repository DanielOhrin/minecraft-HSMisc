package net.highskiesmc.hsmisc.events.handlers;

import dev.rosewood.rosestacker.RoseStacker;
import dev.rosewood.rosestacker.event.AsyncEntityDeathEvent;
import dev.rosewood.rosestacker.event.EntityStackEvent;
import dev.rosewood.rosestacker.manager.StackManager;
import dev.rosewood.rosestacker.stack.StackedEntity;
import net.highskiesmc.hsmisc.HSMisc;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CreeperHealthHandler implements Listener {
    private final HSMisc MAIN;
    private final StackManager stackManager;
    private int maxHealth;

    public void setMaxHealth(int hp) {
        this.maxHealth = hp;
    }

    public CreeperHealthHandler(HSMisc main) {
        this.MAIN = main;
        this.stackManager = RoseStacker.getInstance().getManager(StackManager.class);
        this.maxHealth = main.getConfig().getInt("spawner.creepah.spawn-health", 10);
    }

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent e) {
        if (e.getEntity() instanceof Creeper creepah)
            creepah.setHealth(maxHealth);
    }

    @EventHandler
    public void onMobsDoThings(EntityStackEvent e) {
        if (e.getStack().getEntity() instanceof Creeper creepah)
            creepah.setHealth(maxHealth);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!(e instanceof AsyncEntityDeathEvent) && e.getEntity() instanceof Creeper creepah)
            handleCreeperStackHealth(e, creepah);
    }

    private void handleCreeperStackHealth(EntityDeathEvent e, Creeper creepah) {
        if (this.stackManager.isWorldDisabled(creepah.getWorld()))
            return;

        if (!this.stackManager.isEntityStackingEnabled())
            return;

        StackedEntity stackedEntity = this.stackManager.getStackedEntity(creepah);
        if (stackedEntity == null)
            return;

        new BukkitRunnable() {
            @Override
            public void run() {
                stackedEntity.getEntity().setHealth(maxHealth);
            }
        }.runTaskLater(this.MAIN, 1);
    }
}
