package de.jaunikapauni.axflyboots;

import de.jaunikapauni.axflyboots.listener.PlayerArmorChangeListener;import org.bukkit.plugin.java.JavaPlugin;

public final class AxFlyBoots extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerArmorChangeListener(), this);
        getLogger().info("");
        getLogger().info("----------------------------------------");
        getLogger().info("Name: " + getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info(String.join("Authors: " + ", ", getDescription().getAuthors()));
        getLogger().info("----------------------------------------");
        getLogger().info("");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
