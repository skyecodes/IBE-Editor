package com.github.franckyi.ibeeditor.network.item;

import com.github.franckyi.ibeeditor.network.IMessage;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHand;
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
    public void handle(NetworkEvent.Context context) {
        context.getSender().setHeldItem(EnumHand.MAIN_HAND, itemStack);
    }
}
