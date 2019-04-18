package com.github.franckyi.ibeeditor.client.block.tileentity;

import com.github.franckyi.ibeeditor.client.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.world.LockCode;

public class LockableCategory extends TileEntityCategory<TileEntityLockable> {

    public LockableCategory(BlockEditor editor, TileEntityLockable tileEntity) {
        super(editor, tileEntity);
        this.addAll(new PropertyFormattedText("Lock code", tileEntity.getLockCode().getLock(), s -> tileEntity.setLockCode(new LockCode(s))));
    }
}
