package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.editor.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.editor.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.text.TextComponentString;

public class LootTableCategory extends TileEntityCategory<TileEntityLockableLoot> {

    public LootTableCategory(BlockEditor editor, TileEntityLockableLoot tileEntity) {
        super(editor, tileEntity);
        this.addAll(new PropertyFormattedText("Name", tileEntity.getName().getFormattedText(), s -> tileEntity.setCustomName(new TextComponentString(s))));
    }

}
