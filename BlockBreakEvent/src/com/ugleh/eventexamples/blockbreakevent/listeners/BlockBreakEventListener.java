package com.ugleh.eventexamples.blockbreakevent.listeners;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Collection;

public class BlockBreakEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(player.getGameMode() == GameMode.CREATIVE) return;
        if(!player.hasPermission("bbee.use")) return;

        Inventory inventory = player.getInventory();
        Collection<ItemStack> itemDrops = event.getBlock().getDrops(((PlayerInventory) inventory).getItemInMainHand());

        for(ItemStack itemDrop : itemDrops) {
            for(ItemStack invItem : inventory.getContents()) {
                if(itemDrop.getAmount() == 0) break;
                if(invItem != null && invItem.isSimilar(itemDrop)) {
                    int availableSlotSize = invItem.getMaxStackSize() - invItem.getAmount();
                    if(availableSlotSize == 0) continue;
                    if(itemDrop.getAmount() <= availableSlotSize) {
                        invItem.setAmount(invItem.getAmount() + itemDrop.getAmount());
                        itemDrop.setAmount(0);
                    }else {
                        invItem.setAmount(invItem.getAmount() + availableSlotSize);
                        itemDrop.setAmount(itemDrop.getAmount() - availableSlotSize);
                    }
                }
            }
        }

        World world = event.getBlock().getWorld();
        Location location = event.getBlock().getLocation();

        for(ItemStack itemDrop : itemDrops) {
            if(itemDrop.getAmount() == 0) continue;
            if(inventory.firstEmpty() != -1) {
                inventory.setItem(inventory.firstEmpty(), itemDrop);
                itemDrop.setAmount(0);
            }else{
                world.dropItemNaturally(location, itemDrop);
            }

        }
        event.setDropItems(false);
    }

}