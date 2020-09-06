package com.github.franckyi.ibeeditor.client.gui.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.gui.editor.block.TileEntityCategory;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.text.ITextComponent;

public class LockableCategory extends TileEntityCategory<LockableTileEntity> {

    public LockableCategory(LockableTileEntity tileEntity) {
        super(tileEntity);
        this.addAll(new PropertyFormattedText("Name", tileEntity.getName().getString(), s -> tileEntity.setCustomName(ITextComponent.func_244388_a(s))));
        //this.addAll(new PropertyFormattedText("Lock code", tileEntity.getLockCode().getLock(), s -> tileEntity.setLockCode(new LockCode(s)), 80));
    }
}
