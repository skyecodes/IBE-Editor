package com.github.franckyi.ibeeditor.common.network.editor.item;

import com.github.franckyi.ibeeditor.common.network.IMessage;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

public class MainHandItemEditorMessage implements IMessage {

    protected final ItemStack itemStack;

    public MainHandItemEditorMessage(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public MainHandItemEditorMessage(PacketBuffer buffer) {
        this.itemStack = buffer.readItemStack();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeItemStack(itemStack);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        ctx.getSender().setHeldItem(Hand.MAIN_HAND, itemStack);
    }
}
