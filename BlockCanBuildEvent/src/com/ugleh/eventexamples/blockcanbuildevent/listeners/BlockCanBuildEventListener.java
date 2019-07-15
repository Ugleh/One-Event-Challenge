package com.ugleh.eventexamples.blockcanbuildevent.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;

public class BlockCanBuildEventListener implements Listener {

    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        for(Entity entity : event.getBlock().getWorld().getNearbyEntities(event.getBlock().getLocation(), 1, 1, 1)) {
            if(entity instanceof ArmorStand) {
                event.setBuildable(true);
                break;
            }
        }
    }


}