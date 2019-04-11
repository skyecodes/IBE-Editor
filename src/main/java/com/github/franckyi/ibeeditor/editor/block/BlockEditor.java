package com.github.franckyi.ibeeditor.editor.block;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.editor.AbstractEditor;
import com.github.franckyi.ibeeditor.network.block.BlockEditorMessage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.BlockPos;

public class BlockEditor extends AbstractEditor {

    private final BlockPos blockPos;
    private TileEntity tileEntity;
    private IBlockState blockState;

    public BlockEditor(BlockPos blockPos, TileEntity tileEntity) {
        this.blockPos = blockPos;
        this.tileEntity = mc.world.getTileEntity(blockPos);
        this.tileEntity = tileEntity;
        this.blockState = mc.world.getBlockState(blockPos);
        if (!blockState.getProperties().isEmpty()) {
            this.addCategory("Block state", new BlockStateCategory(this));
        }
        if (tileEntity instanceof IInventory) {
            this.addCategory("Inventory", new InventoryCategory(this, (IInventory) tileEntity));
        }
        if (tileEntity instanceof TileEntityLockableLoot) {
            this.addCategory("Name & Loot table", new LootTableCategory(this, (TileEntityLockableLoot) tileEntity));
        }
        this.show();
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public IBlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(IBlockState blockState) {
        this.blockState = blockState;
    }

    @Override
    protected void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(new BlockEditorMessage(blockPos, blockState, tileEntity));
    }
}
