package com.github.franckyi.ibeeditor.network.block;

import com.github.franckyi.ibeeditor.gui.GuiBlockEditor;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.HashMap;
import java.util.Map;

public class TileEntityUpdateReplyMessage implements IMessage {

    private boolean hasItemHandler;
    private Map<Integer, ItemStack> items = new HashMap<>();

    private boolean hasEnergyStorage;
    private int energyStored;
    private int maxEnergyStored;

    public TileEntityUpdateReplyMessage() {
    }

    public TileEntityUpdateReplyMessage(TileEntity te) {
        if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
            hasItemHandler = true;
            IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            for (int slot = 0; slot < itemHandler.getSlots(); slot++) {
                ItemStack itemStack = itemHandler.getStackInSlot(slot);
                if (!itemStack.isEmpty()) items.put(slot, itemStack);
            }
        }
        if (te.hasCapability(CapabilityEnergy.ENERGY, null)) {
            hasEnergyStorage = true;
            IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, null);
            energyStored = energyStorage.getEnergyStored();
            maxEnergyStored = energyStorage.getMaxEnergyStored();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        hasItemHandler = buf.readBoolean();
        if (hasItemHandler) {
            int size = buf.readInt();
            for (int i = 0; i < size; i++) {
                int slotId = buf.readInt();
                ItemStack itemStack = ByteBufUtils.readItemStack(buf);
                items.put(slotId, itemStack);
            }
        }
        hasEnergyStorage = buf.readBoolean();
        if (hasEnergyStorage) {
            energyStored = buf.readInt();
            maxEnergyStored = buf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(hasItemHandler);
        if (hasItemHandler) {
            buf.writeInt(items.size());
            items.forEach((slotId, itemStack) -> {
                buf.writeInt(slotId);
                ByteBufUtils.writeItemStack(buf, itemStack);
            });
        }
        buf.writeBoolean(hasEnergyStorage);
        if (hasEnergyStorage) {
            buf.writeInt(energyStored);
            buf.writeInt(maxEnergyStored);
        }
    }

    public static class TileEntityUpdateReplyMessageHandler implements IMessageHandler<TileEntityUpdateReplyMessage, IMessage> {

        @Override
        public IMessage onMessage(TileEntityUpdateReplyMessage msg, MessageContext ctx) {
            GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
            if (currentScreen instanceof GuiBlockEditor) {
                GuiBlockEditor editor = (GuiBlockEditor) currentScreen;
                if (msg.hasItemHandler) editor.setInventoryContents(msg.items);
                if (msg.hasEnergyStorage) editor.setEnergy(msg.energyStored, msg.maxEnergyStored);
                editor.refreshCategories();
            }
            return null;
        }
    }

}
