package de.jaunikapauni.axflyboots.listener;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerArmorChangeListener implements Listener {

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent e){
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.CREATIVE){
            return;
        }
        if(!p.hasPermission("axflyboots.use")){
            p.sendMessage("You don't have the permission! [axflyboots.use]");
            return;
        }
        ItemStack newBoots = e.getNewItem();
        ItemStack oldBoots = e.getOldItem();
        if(isBoots(newBoots)){
            if(!p.getAllowFlight()){
                p.setAllowFlight(true);
                p.sendMessage("Fly activated!");
            }
        } else {
            if(isBoots(oldBoots)){
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage("Fly deactived!");
            }
        }
    }

    public boolean isBoots(ItemStack itemStack){
        return itemStack != null && itemStack.getType().name().endsWith("_BOOTS");
    }
}
