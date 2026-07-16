package de.jaunikapauni.axflyboots.listener;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import de.jaunikapauni.axflyboots.AxFlyBoots;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerArmorChangeListener implements Listener {

    AxFlyBoots reference;
    public PlayerArmorChangeListener(AxFlyBoots reference){
        this.reference = reference;
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent e){
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.CREATIVE){
            return;
        }
        if(!p.hasPermission("axflyboots.use")){
            return;
        }
        ItemStack newBoots = e.getNewItem();
        ItemStack oldBoots = e.getOldItem();
        if(isBoots(newBoots)){
            if(!p.getAllowFlight()){
                p.setAllowFlight(true);
                p.sendMessage("Fly activated!");
                reference.getFlyingPlayers().add(p.getUniqueId());

            }
        } else {
            if(isBoots(oldBoots)){
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage("Fly deactivated!");
                reference.getFlyingPlayers().remove(p.getUniqueId());
            }
        }
    }

    public boolean isBoots(ItemStack itemStack){
        return itemStack != null && itemStack.getType().name().endsWith("_BOOTS");
    }
}
