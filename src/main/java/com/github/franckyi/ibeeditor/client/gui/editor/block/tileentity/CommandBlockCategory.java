package com.github.franckyi.ibeeditor.client.gui.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.gui.editor.block.TileEntityCategory;
import net.minecraft.tileentity.TileEntityCommandBlock;

public class CommandBlockCategory extends TileEntityCategory<TileEntityCommandBlock> {

    public CommandBlockCategory(TileEntityCommandBlock tileEntity) {
        super(tileEntity);
        this.addAll(new PropertyFormattedText("Command", tileEntity.getCommandBlockLogic().getCommand(), tileEntity.getCommandBlockLogic()::setCommand));
    }
}
