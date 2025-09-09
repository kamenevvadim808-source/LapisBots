package me.vadim.lapisbots;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class BotManager {

    private final JavaPlugin plugin;
    private final List<Bot> bots = new ArrayList<>();

    public BotManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadBots() {
        bots.clear();
        List<?> botList = plugin.getConfig().getList("bots");
        if (botList != null) {
            for (Object obj : botList) {
                if (obj instanceof ConfigurationSection) {
                    ConfigurationSection section = (ConfigurationSection) obj;
                    String name = section.getString("name");
                    String prefix = section.getString("prefix");
                    List<String> messages = section.getStringList("messages");
                    bots.add(new Bot(name, prefix, messages));
                }
            }
        }
        plugin.getLogger().info("Загружено ботов: " + bots.size());
    }

    public List<Bot> getBots() {
        return bots;
    }

    public Bot getBotByName(String name) {
        for (Bot bot : bots) {
            if (bot.getName().equalsIgnoreCase(name)) {
                return bot;
            }
        }
        return null;
    }
}