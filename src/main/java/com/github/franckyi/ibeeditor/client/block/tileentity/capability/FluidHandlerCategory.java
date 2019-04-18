package com.github.franckyi.ibeeditor.client.block.tileentity.capability;

import com.github.franckyi.ibeeditor.client.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.block.TileEntityCategory;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class FluidHandlerCategory extends TileEntityCategory<IFluidHandler> {

    public FluidHandlerCategory(BlockEditor editor, IFluidHandler fluidHandler) {
        super(editor, fluidHandler); // TODO
    }
}
