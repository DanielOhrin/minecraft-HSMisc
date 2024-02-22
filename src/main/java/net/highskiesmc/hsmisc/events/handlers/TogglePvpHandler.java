package net.highskiesmc.hsmisc.events.handlers;

import net.highskiesmc.hscore.utils.TextUtils;
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
            MAIN.getTogglePvp().set(e.getPlayer().getUniqueId().toString(), !hasPvPEnabled(e.getPlayer()));
            e.getPlayer().sendMessage(hasPvPEnabled(e.getPlayer()) ? TextUtils.translateColor("&4&l[!] &cYou have disabled your PvP in adventure worlds!") : TextUtils.translateColor("&6&l[!] &eYou have enabled your PvP in adventure worlds!"));

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
        return !MAIN.getTogglePvp().isSet(player.getUniqueId().toString()) || MAIN.getTogglePvp().getBoolean(player.getUniqueId().toString(),
                true);
    }
}
