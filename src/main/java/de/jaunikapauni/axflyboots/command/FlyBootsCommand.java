package de.jaunikapauni.axflyboots.command;

import de.jaunikapauni.axflyboots.AxFlyBoots;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyBootsCommand implements CommandExecutor {

    AxFlyBoots reference;
    public FlyBootsCommand(AxFlyBoots reference){
        this.reference = reference;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            return true;
        }
        Player p = (Player) sender;
        reference.toggleFlyBoots(p.getUniqueId());
        if(reference.isFlyBootsEnabled(p.getUniqueId())){
            p.sendMessage("Fly boots enabled!");
        } else {
            p.setFlying(false);
            p.setAllowFlight(false);
            p.sendMessage("Fly boots disabled!");
        }
        return true;
    }
}
