package com.github.franckyi.ibeeditor.client.block;

import com.github.franckyi.ibeeditor.client.Category;
import com.github.franckyi.ibeeditor.client.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.text.TextComponentString;

public class LootTableCategory extends Category {

    public LootTableCategory(BlockEditor editor, TileEntityLockableLoot tileEntity) {
        this.addAll(new PropertyFormattedText("Name", tileEntity.getName().getFormattedText(), s -> tileEntity.setCustomName(new TextComponentString(s))));
    }

}
