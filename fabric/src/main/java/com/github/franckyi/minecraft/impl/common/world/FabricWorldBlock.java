package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.WorldBlock;
import com.github.franckyi.minecraft.impl.common.nbt.FabricTagFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;

public class FabricWorldBlock implements WorldBlock {
    private final BlockPos blockPos;
    private final BlockState state;
    private final BlockEntity blockEntity;

    public FabricWorldBlock(BlockPos blockPos, BlockState state, BlockEntity blockEntity) {
        this.blockPos = blockPos;
        this.state = state;
        this.blockEntity = blockEntity;
    }

    @Override
    public BlockPos getBlockPos() {
        return blockPos;
    }

    @Override
    public CompoundTag getState() {
        return state == null ? null : FabricTagFactory.parseCompound(NbtHelper.fromBlockState(state));
    }

    @Override
    public CompoundTag getData() {
        return blockEntity == null ? null : FabricTagFactory.parseCompound(blockEntity.writeNbt(new NbtCompound()));
    }
}
