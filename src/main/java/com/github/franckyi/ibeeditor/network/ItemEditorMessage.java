package com.github.franckyi.ibeeditor.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ItemEditorMessage {

    private ItemStack itemStack;

    public ItemEditorMessage(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static void encode(ItemEditorMessage msg, PacketBuffer buffer) {
        buffer.writeItemStack(msg.itemStack);
    }

    public static ItemEditorMessage decode(PacketBuffer buffer) {
        return new ItemEditorMessage(buffer.readItemStack());
    }

    public static class Handler {

        public static void handle(ItemEditorMessage msg, Supplier<NetworkEvent.Context> contextSupplier) {
            contextSupplier.get().enqueueWork(() -> work(msg, contextSupplier.get().getSender()));
        }

        private static void work(ItemEditorMessage msg, EntityPlayerMP player) {
            player.setHeldItem(EnumHand.MAIN_HAND, msg.itemStack);
        }

    }
}
