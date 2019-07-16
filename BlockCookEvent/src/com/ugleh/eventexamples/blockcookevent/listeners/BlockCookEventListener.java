package com.ugleh.eventexamples.blockcookevent.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockCookEventListener implements Listener {
    private static final double PERCENT_CHANCE = 0.10;
    private Random random = new Random();

    @EventHandler
    public void onBlockCook(BlockCookEvent event) {
        ItemStack result = event.getResult();
        if(!(event.getBlock().getType() == Material.FURNACE || event.getBlock().getType() == Material.FURNACE_MINECART || event.getBlock().getType() == Material.BLAST_FURNACE)) return;
        if(result.getMaxStackSize() ==  result.getAmount()) return;
        if(random.nextDouble() >= PERCENT_CHANCE) return;
        result.setAmount(result.getAmount() + 1);
    }
}