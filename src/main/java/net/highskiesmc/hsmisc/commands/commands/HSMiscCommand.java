package net.highskiesmc.hsmisc.commands.commands;


import net.highskiesmc.hscore.commands.HSCommand;
import net.highskiesmc.hscore.highskies.HSPlugin;
import net.highskiesmc.hscore.utils.TextUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;

public class HSMiscCommand extends HSCommand {
    public HSMiscCommand(HSPlugin main) {
        super(main);
    }

    @Override
    protected String getPermissionToReload() {
        return "hsmisc.cmd.reload";
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    return reload(sender, "Congrats it reloaded.", "hsmisc.cmd.reload");
                }
            }
        }

        if (hasPermission(
                sender,
                "hsmisc.cmd.tab",
                TextUtils.translateColor(config.get("no-permission", String.class,
                        "&4&l[!] &cYou do not have permission to do that!")))
        ) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    config.get("commands.error", String.class, "&4&l[!] &cUnknown command; " +
                            "Usage: {usage}").replace("{usage}", "/hscore <reload>")));
        }

        return false;
    }
}
