package com.ugleh.eventexamples.blockplaceevent.listeners;

import com.ugleh.eventexamples.blockplaceevent.BlockPlaceEventExample;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class BlockPlaceEventListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        //Does Player have permission to use BPEE?
        if(!event.getPlayer().hasPermission("bpee.use")) return;
        //Is Player placing down the Primed TNT ItemStack created in the main class?
        if(!event.getItemInHand().isSimilar(BlockPlaceEventExample.getInstance().primed_tnt)) return;

        Block blockPlaced = event.getBlockPlaced();
        //Running a task from the scheduler because the event hasn't finished yet, and if we set type to air NOW it would just set it back to TNT.
        Bukkit.getScheduler().runTask(BlockPlaceEventExample.getInstance(), () -> {
            blockPlaced.setType(Material.AIR);
            blockPlaced.getWorld().spawnEntity(blockPlaced.getLocation().add(0.5, 0, 0.5), EntityType.PRIMED_TNT);
        });
    }
}