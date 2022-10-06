package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.common.CommonUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public final class ClientUtil {
    public static void showMessage(Component component) {
        CommonUtil.showMessage(Minecraft.getInstance().player, component);
    }

    public static int convertCreativeInventorySlot(int slot) {
        if (slot == 45) {
            slot = 40;
        } else if (slot >= 36) {
            slot %= 36;
        } else if (slot < 9) {
            slot = 36 + 8 - slot;
        }
        return slot;
    }

    public static int findSlot(ItemStack stack) {
        Inventory inv = Minecraft.getInstance().player.getInventory();
        int slot = inv.getSlotWithRemainingSpace(stack);
        if (slot == -1) {
            slot = inv.getFreeSlot();
        }
        if (slot != -1 && slot < 9) {
            slot += 36;
        }
        return slot;
    }
}
