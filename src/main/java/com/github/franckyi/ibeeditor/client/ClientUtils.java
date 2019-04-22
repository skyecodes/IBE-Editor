package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.client.util.IBENotification;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public final class ClientUtils {

    public static final Minecraft mc = Minecraft.getInstance();

    public static String unformat(String s) {
        return s.replaceAll("§.", "");
    }

    public static void copyGiveCommand(ItemStack itemStack) {
        mc.keyboardListener.setClipboardString(unformat("/give @p "
                + itemStack.getItem().getRegistryName()
                + itemStack.getOrCreateTag()) + " "
                + itemStack.getCount());
        IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Command copied to clipboard.");
    }

    public static void copySummonCommand(Entity entity) {
        NBTTagCompound tag = entity.writeWithoutTypeId(new NBTTagCompound());
        tag.remove("UUIDMost");
        tag.remove("UUIDLeast");
        mc.keyboardListener.setClipboardString(unformat("/summon "
                + entity.getType().getRegistryName() + " ~ ~ ~ " + tag
        ));
        IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Command copied to clipboard.");
    }

}
