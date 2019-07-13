package com.ugleh.eventexamples.blockplaceevent;

import com.ugleh.eventexamples.blockplaceevent.listeners.BlockPlaceEventListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class BlockPlaceEventExample extends JavaPlugin {
    public static BlockPlaceEventExample instance;
    public static ItemStack primed_tnt;

    @Override
    public void onEnable() {
        instance = this;
        createRecipes();
        getServer().getPluginManager().registerEvents(new BlockPlaceEventListener(), this);
    }

    private void createRecipes() {
        primed_tnt = new ItemStack(Material.TNT, 1);
        ItemMeta itemMeta = primed_tnt.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "PRIMED TNT");
        
        List<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_PURPLE + "Placing down this TNT block");
        lore.add(ChatColor.DARK_PURPLE + "will instantly prime it.");
        itemMeta.setLore(lore);

        primed_tnt.setItemMeta(itemMeta);

        NamespacedKey key = new NamespacedKey(this, "primed_tnt");
        ShapelessRecipe recipe = new ShapelessRecipe(key, primed_tnt);
        recipe.addIngredient(Material.TNT);
        recipe.addIngredient(Material.REDSTONE);

        getServer().addRecipe(recipe);

    }


    public static BlockPlaceEventExample getInstance()
    {
        return instance;
    }
}