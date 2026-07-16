package de.jaunikapauni.axflyboots;

import de.jaunikapauni.axeconomy.AxEconomy;
import de.jaunikapauni.axeconomy.api.EconomyAPI;
import de.jaunikapauni.axflyboots.listener.PlayerArmorChangeListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class AxFlyBoots extends JavaPlugin {
    EconomyAPI economyAPI;
    public EconomyAPI getEconomyAPI(){
        return economyAPI;
    }
    Set<UUID> flyingPlayers = new HashSet<>();
    public Set<UUID> getFlyingPlayers(){
        return flyingPlayers;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        AxEconomy axEconomy = (AxEconomy) Bukkit.getPluginManager().getPlugin("AxEconomy");
        if(axEconomy == null){
            getLogger().severe("AxEconomy not found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        economyAPI = axEconomy.getEconomyAPI();
        getServer().getPluginManager().registerEvents(new PlayerArmorChangeListener(this), this);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for(UUID uuid : flyingPlayers){
                Player p = Bukkit.getPlayer(uuid);
                if(p.getGameMode() == GameMode.CREATIVE){
                    continue;
                }
                if(!p.isFlying()){
                    continue;
                }
                double balance =  economyAPI.getBalance(p.getUniqueId());
                if(balance < 20){
                    p.sendActionBar(ChatColor.RED + "You don't have enough money to fly!");
                    p.setFlying(false);
                    p.setAllowFlight(false);
                    continue;
                }
                economyAPI.withdraw(p.getUniqueId(), 20);
                p.sendActionBar(ChatColor.RED + "[AxFlyBoots] 20€");
        }
        }, 0L, 20L);
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
