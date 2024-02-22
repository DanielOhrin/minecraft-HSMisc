package net.highskiesmc.hsmisc.commands.commands;

import net.highskiesmc.hscore.utils.TextUtils;
import net.highskiesmc.hscore.utils.item.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class EncodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player) || !player.hasPermission("")) {
            sender.sendMessage(TextUtils.translateColor("&4&l[!] &cOnly players can use this command!"));
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType().isAir()) {
            player.sendMessage(TextUtils.translateColor("&4&l[!] &cYou must hold an item in your hand!"));
            return false;
        }

        try {
            Bukkit.getLogger().info(ItemUtils.itemStackToBase64(item));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        player.sendMessage(TextUtils.translateColor("&6&l[!] &eThe encoded item was printed into control."));
        return true;
    }
}
