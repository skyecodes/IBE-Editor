package com.github.franckyi.ibeeditor.client.block.tileentity;

import com.github.franckyi.ibeeditor.client.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.text.TextComponentString;

public class LootTableCategory extends TileEntityCategory<TileEntityLockableLoot> {

    public LootTableCategory(BlockEditor editor, TileEntityLockableLoot tileEntity) {
        super(editor, tileEntity);
        this.addAll(new PropertyFormattedText("Name", tileEntity.getName().getFormattedText(), s -> tileEntity.setCustomName(new TextComponentString(s))));
    }

}
