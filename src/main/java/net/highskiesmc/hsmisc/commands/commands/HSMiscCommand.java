package net.highskiesmc.hsmisc.commands.commands;

import net.highskiesmc.hscore.commands.HSCommand;
import net.highskiesmc.hscore.highskies.HSPlugin;
import net.highskiesmc.hscore.utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
                    return reload(sender, TextUtils.translateColor("&6&l[!] &eYou have successfully reloaded HSMisc!"), "hsmisc.cmd.reload");
                }
            }
        }

        if (hasPermission(sender, "hsmisc.cmd.tab", TextUtils.translateColor("&4&l[!] &cYou do not have permission to do that!"))) {
            sender.sendMessage(TextUtils.translateColor("Usage: /hscore <reload>"));
        }

        return false;
    }
}
