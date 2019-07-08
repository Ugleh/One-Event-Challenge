package com.ugleh.eventexamples.blockbreakevent;

import com.ugleh.eventexamples.blockbreakevent.listeners.BlockBreakEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBreakEventExample extends JavaPlugin {

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
    }
}
