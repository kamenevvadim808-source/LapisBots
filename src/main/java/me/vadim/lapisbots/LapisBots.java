package me.vadim.lapisbots;

import org.bukkit.plugin.java.JavaPlugin;

public class LapisBots extends JavaPlugin {

    private BotManager botManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        botManager = new BotManager(this);
        botManager.loadBots();
        getCommand("lapisbots").setExecutor(new ReloadCommand(this));
        getLogger().info("LapisBots включён!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LapisBots выключен.");
    }

    public BotManager getBotManager() {
        return botManager;
    }
}