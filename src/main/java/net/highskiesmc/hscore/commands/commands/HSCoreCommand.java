package net.highskiesmc.hscore.commands.commands;

import com.mattisadev.mcore.highskies.HSCommand;
import com.mattisadev.mcore.highskies.HSPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HSCoreCommand extends HSCommand {
    public HSCoreCommand(HSPlugin main) {
        super(main);
    }

    @Override
    protected String getPermissionToReload() {
        return "hscore.cmd.reload";
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    return reload(sender);
                }
            }
        }

        if (hasPermission(sender, "hscore.cmd.tab")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    this.MAIN.getMessages().getString("commands.error", "&4&l[!] &cUnknown command; " +
                            "Usage: {usage}").replace("{usage}", "/hscore <reload>")));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    this.MAIN.getMessages().getString("no-permission", "&4&l[!] &cYou do not have permission to do " +
                            "that!")));
        }

        return false;
    }
}
