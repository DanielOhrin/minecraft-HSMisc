package net.highskiesmc.hsmisc;

import net.highskiesmc.hscore.configuration.sources.FileConfigSource;
import net.highskiesmc.hscore.highskies.HSPlugin;
import net.highskiesmc.hsmisc.commands.commands.EncodeCommand;
import net.highskiesmc.hsmisc.commands.commands.HSMiscCommand;
import net.highskiesmc.hsmisc.commands.tabcompleters.HSMiscTabCompleter;
import net.highskiesmc.hsmisc.events.handlers.*;
import org.bukkit.Bukkit;

public final class HSMisc extends HSPlugin {
    public static final boolean usingRoseStacker = Bukkit.getPluginManager().getPlugin("RoseStacker") != null;
    public static final boolean usingEliteBosses = Bukkit.getPluginManager().getPlugin("EliteBosses") != null;
    private CreeperHealthHandler creeperHealthHandler;

    public void enable() {
        config.addSource(new FileConfigSource("config.yml", this));
        config.addSource(new FileConfigSource("messages.yml", this));
        config.addSource(new FileConfigSource("damage-modifiers.yml", this));
        config.reload();

        getCommand("hsmisc").setExecutor(new HSMiscCommand(this));
        getCommand("hsmisc").setTabCompleter(new HSMiscTabCompleter());

        getCommand("encode").setExecutor(new EncodeCommand());

        register(new EntityDamageHandler(this));
        register(new ExplosionHandler(this));
        register(new TogglePvpHandler(this));

        if (usingRoseStacker) {
            this.creeperHealthHandler = new CreeperHealthHandler(this);
            register(this.creeperHealthHandler);
        }

        if (usingEliteBosses) {
            Bukkit.getPluginManager().registerEvents(new BossClearDropsHandler(), this);
        }
    }

    @Override
    public void disable() {

    }

    @Override
    public void reload() {
        if (usingRoseStacker) {
            creeperHealthHandler.setMaxHealth(config.get("spawner.creepah.spawn-health", int.class, 10));
        }
    }

    @Override
    protected boolean isUsingInventories() {
        return false;
    }
}
