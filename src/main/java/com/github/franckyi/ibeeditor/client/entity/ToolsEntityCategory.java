package com.github.franckyi.ibeeditor.client.entity;

import com.github.franckyi.ibeeditor.IBEUtils;
import com.github.franckyi.ibeeditor.client.Category;
import com.github.franckyi.ibeeditor.client.property.ButtonProperty;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public class ToolsEntityCategory extends Category {

    private final Entity entity;

    public ToolsEntityCategory(Entity entity) {
        this.entity = entity;
        this.addAll(
                new ButtonProperty("Copy /summon command", this::copySummonCommand, TextFormatting.RED +
                        "Be careful !", "The text formatting won't be copied", "because it doesn't work with commands."),
                new ButtonProperty("Copy to IBE clipboard", this::copyToClipboard)
        );
    }

    private void copyToClipboard() {
        //Clipboard.getInstance().getClipboardEntities().add(entity);
    }

    private void copySummonCommand() {
        NBTTagCompound tag = entity.writeWithoutTypeId(new NBTTagCompound());
        tag.remove("UUIDMost");
        tag.remove("UUIDLeast");
        mc.keyboardListener.setClipboardString(IBEUtils.unformat("/summon "
                + entity.getType().getRegistryName() + " ~ ~ ~ " + tag
        ));
    }
}
