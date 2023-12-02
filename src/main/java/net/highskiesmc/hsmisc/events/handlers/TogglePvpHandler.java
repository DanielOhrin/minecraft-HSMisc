package net.highskiesmc.hsmisc.events.handlers;

import net.highskiesmc.hsmisc.HSMisc;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TogglePvpHandler implements Listener {
    private final HSMisc MAIN;

    public TogglePvpHandler(HSMisc main) {
        this.MAIN = main;
    }

    @EventHandler
    public void onTogglePvP(PlayerCommandPreprocessEvent e) {
        final String[] ARGS = e.getMessage().split(" ");

        if (ARGS[0].equalsIgnoreCase("/pvp") || ARGS[0].equalsIgnoreCase("/togglepvp")) {
            final FileConfiguration CONFIG = this.MAIN.getTogglePvp();

            boolean isEnabled = hasPvPEnabled(e.getPlayer());

            CONFIG.set(e.getPlayer().getUniqueId().toString(), !isEnabled);

            e.getPlayer().sendMessage(
                    isEnabled
                            ? ChatColor.RED + "PvP in adventures disabled"
                            : ChatColor.GREEN + "PvP in adventures enabled"
            );

            MAIN.getConfigManager().save("togglepvp.yml");

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
        final FileConfiguration CONFIG = this.MAIN.getTogglePvp();

        return !CONFIG.isSet(player.getUniqueId().toString()) || CONFIG.getBoolean(player.getUniqueId().toString(),
                true);
    }
}
