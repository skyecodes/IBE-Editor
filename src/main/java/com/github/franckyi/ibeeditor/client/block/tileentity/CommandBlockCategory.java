package com.github.franckyi.ibeeditor.client.block.tileentity;

import com.github.franckyi.ibeeditor.client.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityCommandBlock;

public class CommandBlockCategory extends TileEntityCategory<TileEntityCommandBlock> {

    public CommandBlockCategory(BlockEditor editor, TileEntityCommandBlock tileEntity) {
        super(editor, tileEntity);
        this.addAll(new PropertyFormattedText("Command", tileEntity.getCommandBlockLogic().getCommand(), tileEntity.getCommandBlockLogic()::setCommand));
    }
}
