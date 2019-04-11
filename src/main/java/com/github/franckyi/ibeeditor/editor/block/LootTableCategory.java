package com.github.franckyi.ibeeditor.editor.block;

import com.github.franckyi.ibeeditor.editor.Category;
import com.github.franckyi.ibeeditor.editor.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.text.TextComponentString;

public class LootTableCategory extends Category {

    public LootTableCategory(BlockEditor editor, TileEntityLockableLoot tileEntity) {
        this.addAll(new PropertyFormattedText("Name", tileEntity.getName().getFormattedText(), s -> tileEntity.setCustomName(new TextComponentString(s))));
    }

}
