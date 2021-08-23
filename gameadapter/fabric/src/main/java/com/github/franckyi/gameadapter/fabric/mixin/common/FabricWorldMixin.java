package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.world.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class FabricWorldMixin implements WorldAccess, AutoCloseable, IWorld {
    @Shadow
    public abstract boolean setBlockState(BlockPos pos, BlockState state);

    @Shadow
    @Nullable
    public abstract Entity getEntityById(int id);

    @Override
    public void setBlockInventoryItem(IBlockPos blockPos, int slotId, IItemStack itemStack) {
        BlockEntity blockEntity = getBlockEntity((BlockPos) blockPos);
        if (blockEntity instanceof Inventory) {
            ((Inventory) blockEntity).setStack(slotId, ItemStack.class.cast(itemStack));
        }
    }

    @Override
    public BlockData getBlockData(IBlockPos blockPos) {
        BlockState blockState = getBlockState((BlockPos) blockPos);
        ICompoundTag blockTag = null;
        BlockEntity blockEntity = getBlockEntity((BlockPos) blockPos);
        if (blockEntity != null) {
            blockTag = (ICompoundTag) blockEntity.writeNbt(new NbtCompound());
        }
        return new BlockData((IBlockState) blockState, blockTag);
    }

    @Override
    public void setBlockData(WorldBlockData data) {
        BlockPos pos = (BlockPos) data.getPos();
        BlockState state = (BlockState) data.getState();
        setBlockState(pos, state);
        BlockEntity blockEntity = getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.fromTag(state, (NbtCompound) data.getTag());
        }
    }

    @Override
    public ICompoundTag getEntity(int entityId) {
        Entity entity = getEntityById(entityId);
        return entity == null ? null : (ICompoundTag) entity.writeNbt(new NbtCompound());
    }

    @Override
    public void setEntityData(int entityId, ICompoundTag data) {
        getEntityById(entityId).readNbt((NbtCompound) data);
    }
}
