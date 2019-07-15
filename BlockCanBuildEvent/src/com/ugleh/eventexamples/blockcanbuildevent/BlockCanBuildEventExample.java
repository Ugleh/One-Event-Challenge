package com.ugleh.eventexamples.blockcanbuildevent;

import com.ugleh.eventexamples.blockcanbuildevent.listeners.BlockCanBuildEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockCanBuildEventExample extends JavaPlugin {
    public static BlockCanBuildEventExample instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new BlockCanBuildEventListener(), this);
    }


    public static BlockCanBuildEventExample getInstance() {
        return instance;
    }
}