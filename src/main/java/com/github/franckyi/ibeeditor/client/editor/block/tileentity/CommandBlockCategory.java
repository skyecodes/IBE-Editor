package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.editor.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.editor.property.PropertyFormattedText;
import net.minecraft.tileentity.TileEntityCommandBlock;

public class CommandBlockCategory extends TileEntityCategory<TileEntityCommandBlock> {

    public CommandBlockCategory(BlockEditor editor, TileEntityCommandBlock tileEntity) {
        super(editor, tileEntity);
        this.addAll(new PropertyFormattedText("Command", tileEntity.getCommandBlockLogic().getCommand(), tileEntity.getCommandBlockLogic()::setCommand));
    }
}
