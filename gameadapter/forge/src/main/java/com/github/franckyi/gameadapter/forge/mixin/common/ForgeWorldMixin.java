package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.*;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class ForgeWorldMixin extends CapabilityProvider<World> implements net.minecraft.world.IWorld, AutoCloseable, IForgeWorld, IWorld {
    @Shadow
    public abstract Entity shadow$getEntity(int id);

    @Shadow
    public abstract boolean setBlockAndUpdate(BlockPos p_175656_1_, BlockState p_175656_2_);

    protected ForgeWorldMixin(Class<World> baseClass) {
        super(baseClass);
    }

    @Override
    public void setBlockInventoryItem(IBlockPos blockPos, int slotId, IItemStack itemStack) {
        TileEntity blockEntity = getBlockEntity((BlockPos) blockPos);
        if (blockEntity instanceof IInventory) {
            ((IInventory) blockEntity).setItem(slotId, ItemStack.class.cast(itemStack));
        }
    }

    @Override
    public BlockData getBlockData(IBlockPos blockPos) {
        BlockState blockState = getBlockState((BlockPos) blockPos);
        ICompoundTag blockTag = null;
        TileEntity blockEntity = getBlockEntity((BlockPos) blockPos);
        if (blockEntity != null) {
            blockTag = (ICompoundTag) blockEntity.save(new CompoundNBT());
        }
        return new BlockData((IBlockState) blockState, blockTag);
    }

    @Override
    public void setBlockData(WorldBlockData data) {
        BlockPos pos = (BlockPos) data.getPos();
        BlockState state = (BlockState) data.getState();
        setBlockAndUpdate(pos, state);
        TileEntity blockEntity = getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.load(state, (CompoundNBT) data.getTag());
        }
    }

    @Override
    public ICompoundTag getEntity(int entityId) {
        Entity entity = shadow$getEntity(entityId);
        return entity == null ? null : (ICompoundTag) entity.saveWithoutId(new CompoundNBT());
    }

    @Override
    public void setEntityData(int entityId, ICompoundTag data) {
        shadow$getEntity(entityId).load((CompoundNBT) data);
    }
}
