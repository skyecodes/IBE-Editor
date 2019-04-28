package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityCommandBlock;

public class CommandBlockCategory extends TileEntityCategory<TileEntityCommandBlock> {

    public CommandBlockCategory(TileEntityCommandBlock tileEntity) {
        super(tileEntity);
        this.addAll(new PropertyFormattedText("Command", tileEntity.getCommandBlockLogic().getCommand(), tileEntity.getCommandBlockLogic()::setCommand));
    }
}
