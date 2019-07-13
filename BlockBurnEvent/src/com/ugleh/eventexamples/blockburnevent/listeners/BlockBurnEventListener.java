package com.ugleh.eventexamples.blockburnevent.listeners;

import com.ugleh.eventexamples.blockburnevent.BlockBurnEventExample;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Random;


public class BlockBurnEventListener implements Listener {
    private String[] woodTypes = {"WOOD", "SPRUCE", "ACACIA", "DARK_OAK", "OAK", "BIRCH", "JUNGLE"};
    private String[] logType = {"_WOOD", "LOG"};
    private Random random = new Random();
    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        String materialName = event.getBlock().getType().name().toUpperCase();

        //If the Material is Leaves OR does not contain one of the strings in woodTypes return and stop here.
        if((materialName.contains("LEAVES")) || (!stringContainsItemFromList(materialName,woodTypes))) return;

        //If the wood type is LOG, or WOOD but not a LEGACY item then ALWAYS drop 1 charcoal, otherwise 25% chance.
        if(stringContainsItemFromList(materialName,logType) && (!materialName.contains("LEGACY")))  {
            Bukkit.getScheduler().runTask(BlockBurnEventExample.getInstance(), () -> event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.CHARCOAL, 1)));
        }else {
            float chance = random.nextFloat();
            if(!(chance <= 0.25f)) return;
            Bukkit.getScheduler().runTask(BlockBurnEventExample.getInstance(), () -> event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.CHARCOAL, 1)));
        }
    }


    private static boolean stringContainsItemFromList(String input, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(input::contains);
    }
}