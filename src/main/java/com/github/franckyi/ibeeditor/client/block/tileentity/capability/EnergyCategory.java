package com.github.franckyi.ibeeditor.client.block.tileentity.capability;

import com.github.franckyi.ibeeditor.client.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.block.TileEntityCategory;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyCategory extends TileEntityCategory<IEnergyStorage> {

    public EnergyCategory(BlockEditor editor, IEnergyStorage energyStorage) {
        super(editor, energyStorage); // TODO
    }

}
