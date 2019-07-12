package com.ugleh.eventexamples.blockplaceevent;

import com.ugleh.eventexamples.blockplaceevent.listeners.BlockPlaceEventListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockPlaceEventExample extends JavaPlugin {
    ItemStack sheepSkinnerItem;
    BlockPlaceEventExample instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new BlockPlaceEventListener(), this);
        createRecipe();
    }
    private void createRecipe() {
        sheepSkinnerItem = new ItemStack(Material.REDSTONE_TORCH, 1);
        ItemMeta ssMeta = sheepSkinnerItem.getItemMeta();
        ssMeta.setDisplayName(ChatColor.GOLD + "Solar Powered Sheep Skinner");
        sheepSkinnerItem.setItemMeta(ssMeta);

        ShapedRecipe ssRecipe;
        NamespacedKey key = new NamespacedKey(this, this.getDescription().getName());

        ssRecipe = new ShapedRecipe(key, sheepSkinnerItem);
        ssRecipe.shape("-D-", "-S-", "---");
        ssRecipe.setIngredient('D', Material.DAYLIGHT_DETECTOR);
        ssRecipe.setIngredient('S', Material.SHEARS);
        this.getServer().addRecipe(ssRecipe);
    }

    public BlockPlaceEventExample getInstance()
    {
        return this.instance;
    }
}
