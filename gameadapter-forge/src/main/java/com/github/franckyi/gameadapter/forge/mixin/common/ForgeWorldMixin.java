package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.world.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Level.class)
public abstract class ForgeWorldMixin extends CapabilityProvider<Level> implements net.minecraft.world.level.LevelAccessor, AutoCloseable, IForgeLevel, IWorld {
    @Shadow
    public abstract Entity shadow$getEntity(int id);

    @Shadow
    public abstract boolean setBlockAndUpdate(BlockPos p_175656_1_, BlockState p_175656_2_);

    protected ForgeWorldMixin(Class<Level> baseClass) {
        super(baseClass);
    }

    @Override
    public void setBlockInventoryItem(IBlockPos blockPos, int slotId, IItemStack itemStack) {
        BlockEntity blockEntity = getBlockEntity((BlockPos) blockPos);
        if (blockEntity instanceof Container) {
            ((Container) blockEntity).setItem(slotId, ItemStack.class.cast(itemStack));
        }
    }

    @Override
    public BlockData getBlockData(IBlockPos blockPos) {
        BlockState blockState = getBlockState((BlockPos) blockPos);
        ICompoundTag blockTag = null;
        BlockEntity blockEntity = getBlockEntity((BlockPos) blockPos);
        if (blockEntity != null) {
            blockTag = (ICompoundTag) blockEntity.save(new CompoundTag());
        }
        return new BlockData((IBlockState) blockState, blockTag);
    }

    @Override
    public void setBlockData(WorldBlockData data) {
        BlockPos pos = (BlockPos) data.getPos();
        BlockState state = (BlockState) data.getState();
        setBlockAndUpdate(pos, state);
        BlockEntity blockEntity = getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.load((CompoundTag) data.getTag());
        }
    }

    @Override
    public ICompoundTag getEntity(int entityId) {
        Entity entity = shadow$getEntity(entityId);
        return entity == null ? null : (ICompoundTag) entity.saveWithoutId(new CompoundTag());
    }

    @Override
    public void setEntityData(int entityId, ICompoundTag data) {
        shadow$getEntity(entityId).load((CompoundTag) data);
    }
}
