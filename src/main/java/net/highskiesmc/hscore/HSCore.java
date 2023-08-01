package net.highskiesmc.hscore;

import com.mattisadev.mcore.highskies.HSPlugin;
import net.highskiesmc.hscore.commands.commands.HSCoreCommand;
import net.highskiesmc.hscore.commands.tabcompleters.HSCoreTabCompleter;
import net.highskiesmc.hscore.events.handlers.ExplosionHandler;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public final class HSCore extends HSPlugin {
    @Override
    public void enable() {
        getCommand("hscore").setExecutor(new HSCoreCommand(this));
        getCommand("hscore").setTabCompleter(new HSCoreTabCompleter());

        register(new ExplosionHandler(this));
    }

    @Override
    public void disable() {

    }

    @Override
    public void reload() {

    }

    @Override
    protected boolean isUsingInventories() {
        return false;
    }

    @Nonnull
    @Override
    protected Set<String> getConfigFileNames() {
        return new HashSet<>();
    }
}
