package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.world.LockCode;

public class LockableCategory extends TileEntityCategory<TileEntityLockable> {

    public LockableCategory(TileEntityLockable tileEntity) {
        super(tileEntity);
        this.addAll(new PropertyFormattedText("Lock code", tileEntity.getLockCode().getLock(), s -> tileEntity.setLockCode(new LockCode(s)), 80));
    }
}
