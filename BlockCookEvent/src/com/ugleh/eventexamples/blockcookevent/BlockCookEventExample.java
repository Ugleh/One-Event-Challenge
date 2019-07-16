package com.ugleh.eventexamples.blockcookevent;

import com.ugleh.eventexamples.blockcookevent.listeners.BlockCookEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockCookEventExample extends JavaPlugin {
    public static BlockCookEventExample instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new BlockCookEventListener(), this);
    }


    public static BlockCookEventExample getInstance() {
        return instance;
    }
}