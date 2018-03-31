package com.github.franckyi.ibeeditor.util.item;

import net.minecraft.client.Minecraft;
import net.minecraft.network.NetHandlerPlayServer;

public class HeldItemHandler extends BaseItemHandler {

    protected HeldItemHandler() {
    }

    public HeldItemHandler(int zero) {
        super(Minecraft.getMinecraft().player.getHeldItemMainhand());
    }

    @Override
    public void updateItemStack(NetHandlerPlayServer server) {
        server.player.inventory.mainInventory.set(server.player.inventory.currentItem, getItemStack());
    }
}
