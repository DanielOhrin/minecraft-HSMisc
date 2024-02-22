package net.highskiesmc.hsmisc;

import net.highskiesmc.hsmisc.commands.commands.EncodeCommand;
import net.highskiesmc.hscore.configuration.ConfigManager;
import net.highskiesmc.hscore.highskies.HSPlugin;
import net.highskiesmc.hsmisc.commands.commands.HSMiscCommand;
import net.highskiesmc.hsmisc.commands.tabcompleters.HSMiscTabCompleter;
import net.highskiesmc.hsmisc.events.handlers.ExplosionHandler;
import net.highskiesmc.hsmisc.events.handlers.CreeperHealthHandler;
import net.highskiesmc.hsmisc.events.handlers.TogglePvpHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public final class HSMisc extends HSPlugin {
    public static final boolean USING_ROSESTACKER = Bukkit.getPluginManager().getPlugin("RoseStacker") != null;
    private CreeperHealthHandler creeperHealthHandler;
    private ConfigManager configManager;
    @Override
    public void enable() {

        this.configManager = new ConfigManager(this);

        configManager.load("config.yml");
        configManager.save("config.yml");
        configManager.load("togglepvp.yml");
        configManager.save("togglepvp.yml");

        getCommand("hsmisc").setExecutor(new HSMiscCommand(this));
        getCommand("hsmisc").setTabCompleter(new HSMiscTabCompleter());

        getCommand("encode").setExecutor(new EncodeCommand());

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
            this.creeperHealthHandler.setMaxHealth(getConfiguration().getInt("creeper-spawner-health", 10));
    }

    @Override
    protected boolean isUsingInventories() {
        return false;
    }

    public FileConfiguration getTogglePvp() {
        return ConfigManager.get("togglepvp.yml");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public FileConfiguration getConfiguration() {
        return ConfigManager.get("config.yml");
    }
}
