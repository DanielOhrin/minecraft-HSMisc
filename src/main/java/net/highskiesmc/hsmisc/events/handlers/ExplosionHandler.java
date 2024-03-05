package net.highskiesmc.hsmisc.events.handlers;

import net.highskiesmc.hscore.highskies.HSListener;
import net.highskiesmc.hsmisc.HSMisc;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionHandler extends HSListener {

    public ExplosionHandler(HSMisc main) {
        super(main);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        Boolean allowExplosionGrief = config.get("allow-explosion-grief", boolean.class, null);

        if (allowExplosionGrief == null) {
            main.getLogger().warning("Missing config setting \"allow-explosion-grief\": bool");
        } else if (!allowExplosionGrief) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        Boolean allowExplosionGrief = config.get("allow-explosion-grief", boolean.class, null);

        if (allowExplosionGrief == null) {
            main.getLogger().warning("Missing config setting \"allow-explosion-grief\": bool");
        } else if (!allowExplosionGrief) {
            e.setCancelled(true);
        }
    }
}
