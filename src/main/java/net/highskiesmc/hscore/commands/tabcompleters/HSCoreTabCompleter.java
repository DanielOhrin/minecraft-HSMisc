package net.highskiesmc.hscore.commands.tabcompleters;

import com.mattisadev.mcore.highskies.HSTabCompleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class HSCoreTabCompleter extends HSTabCompleter {
    @Override
    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission("hscore.cmd.tab");
    }

    @Override
    public List<String> getResults(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, @NonNull String[] args) {
        List<String> result = new ArrayList<>();

        switch (args.length) {
            case 1 -> {
                result.add("reload");
            }
        }

        return result;
    }
}
