package net.highskiesmc.hscore.events.handlers;

import net.highskiesmc.hscore.HSCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionHandler implements Listener {
    private final HSCore MAIN;

    public ExplosionHandler(HSCore main) {
        this.MAIN = main;
        System.out.println("Registered event");
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        if (!MAIN.getConfig().isSet("allow-explosion-grief")) {
            Bukkit.getLogger().warning("Missing config setting \"allow-explosion-grief\": bool");
        }

        if (!MAIN.getConfig().getBoolean("allow-explosion-grief")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!MAIN.getConfig().isSet("allow-explosion-grief")) {
            Bukkit.getLogger().warning("Missing config setting \"allow-explosion-grief\": bool");
        }

        if (!MAIN.getConfig().getBoolean("allow-explosion-grief")) {
            e.setCancelled(true);
        }
    }
}
