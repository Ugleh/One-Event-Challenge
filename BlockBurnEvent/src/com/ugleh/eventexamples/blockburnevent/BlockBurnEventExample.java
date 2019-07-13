package com.ugleh.eventexamples.blockburnevent;

import com.ugleh.eventexamples.blockburnevent.listeners.BlockBurnEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBurnEventExample extends JavaPlugin {
    public static BlockBurnEventExample instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new BlockBurnEventListener(), this);
    }


    public static BlockBurnEventExample getInstance() {
        return instance;
    }
}