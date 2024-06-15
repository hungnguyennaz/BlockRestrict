package com.sylvcraft.events;

import com.sylvcraft.BlockRestrict;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class BlockPlace implements Listener {
    BlockRestrict plugin;

    public BlockPlace(BlockRestrict instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        List<String> blocks = this.plugin.getConfig().getStringList("blocks");
        if (blocks.contains(e.getBlockPlaced().getType().name())) {
            this.plugin.msg("not-allowed", e.getPlayer());
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        List<String> blocks = this.plugin.getConfig().getStringList("blocks");
        String itemType = e.getItemDrop().getItemStack().getType().name();
        if (blocks.contains(itemType)) {
            this.plugin.msg("not-allowed", e.getPlayer());
            e.setCancelled(true);
        }
    }
}