package net.highskiesmc.hsmisc.events.handlers;

import net.highskiesmc.hscore.highskies.HSListener;
import net.highskiesmc.hscore.highskies.HSPlugin;
import net.highskiesmc.hsmisc.HSMisc;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TogglePvpHandler extends HSListener {
    private final NamespacedKey pvpToggleKey;
    public TogglePvpHandler(HSPlugin main) {
        super(main);
        this.pvpToggleKey = new NamespacedKey(main, "togglePvP");
    }

    @EventHandler
    public void onTogglePvP(PlayerCommandPreprocessEvent e) {
        final String[] args = e.getMessage().split(" ");

        if (args[0].equalsIgnoreCase("/pvp") || args[0].equalsIgnoreCase("/togglepvp")) {
            Player player = e.getPlayer();

            boolean isEnabled = hasPvPEnabled(player);
            togglePvp(player);

            player.sendMessage(
                    isEnabled
                            ? ChatColor.RED + "PvP in adventures disabled"
                            : ChatColor.GREEN + "PvP in adventures enabled"
            );


            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPvP(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player attacker) {
            if (e.getEntity() instanceof Player victim) {
                if (!hasPvPEnabled(attacker)) {
                    e.setCancelled(true);

                    if (e.getCause() == EntityDamageEvent.DamageCause.THORNS) {
                        victim.damage(e.getDamage());
                    }
                }
            }
        }
    }

    private boolean hasPvPEnabled(@NonNull Player player) {
        return player.getPersistentDataContainer().getOrDefault(pvpToggleKey, PersistentDataType.STRING, "off").equalsIgnoreCase("on");
    }

    private void togglePvp(@NonNull Player player) {
        String value = hasPvPEnabled(player) ? "off" : "on";
        player.getPersistentDataContainer().set(pvpToggleKey, PersistentDataType.STRING, value);
    }
}
