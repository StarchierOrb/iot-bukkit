package me.starorbbb.iotbukkit.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onInteractBlock(PlayerInteractEvent evt) {
        evt.getClickedBlock().getLocation();
    }
}
