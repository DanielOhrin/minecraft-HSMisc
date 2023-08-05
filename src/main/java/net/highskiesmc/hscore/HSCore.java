package net.highskiesmc.hscore;

import com.mattisadev.mcore.configuration.ConfigManager;
import com.mattisadev.mcore.highskies.HSPlugin;
import net.highskiesmc.hscore.commands.commands.HSCoreCommand;
import net.highskiesmc.hscore.commands.tabcompleters.HSCoreTabCompleter;
import net.highskiesmc.hscore.events.handlers.ExplosionHandler;
import net.highskiesmc.hscore.events.handlers.CreeperHealthHandler;
import net.highskiesmc.hscore.events.handlers.TogglePvpHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public final class HSCore extends HSPlugin {
    public static final boolean USING_ROSESTACKER = Bukkit.getPluginManager().getPlugin("RoseStacker") != null;
    private CreeperHealthHandler creeperHealthHandler;
    @Override
    public void enable() {
        getCommand("hscore").setExecutor(new HSCoreCommand(this));
        getCommand("hscore").setTabCompleter(new HSCoreTabCompleter());

        register(new ExplosionHandler(this));
        register(new TogglePvpHandler(this));

        if (USING_ROSESTACKER) {
            this.creeperHealthHandler = new CreeperHealthHandler(this);
            register(this.creeperHealthHandler);
        }
    }

    @Override
    public void disable() {

    }

    @Override
    public void reload() {
        if (USING_ROSESTACKER)
            this.creeperHealthHandler.setMaxHealth(getConfig().getInt("spawner.creepah.spawn-health", 10));
    }

    @Override
    protected boolean isUsingInventories() {
        return false;
    }

    @Nonnull
    @Override
    protected Set<String> getConfigFileNames() {
        return new HashSet<>() {{
            add("togglepvp.yml");
        }};
    }

    public FileConfiguration getTogglePvp() {
        return ConfigManager.get("togglepvp.yml");
    }
}
