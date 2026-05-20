package de.jaunikapauni.axflyboots;

import de.jaunikapauni.axflyboots.listener.PlayerArmorChangeListener;import org.bukkit.plugin.java.JavaPlugin;

public final class AxFlyBoots extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerArmorChangeListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
