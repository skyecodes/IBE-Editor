package com.github.franckyi.ibeeditor.client.context;

import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.function.Consumer;

public class BlockEditorContext extends EditorContext<BlockEditorContext> {
    private BlockState blockState;
    private final BlockEntity blockEntity;

    public BlockEditorContext(BlockState blockState, CompoundTag tag, Component errorTooltip, Consumer<BlockEditorContext> action) {
        super(tag, errorTooltip, false, action);
        this.blockState = blockState;
        this.blockEntity = tag == null ? null : BlockEntity.loadStatic(BlockPos.ZERO, blockState, tag);
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public <T extends Comparable<T>> void updateBlockState(Property<T> property, T value) {
        blockState = blockState.setValue(property, value);
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public MutableComponent getTargetName() {
        return ModTexts.BLOCK;
    }

    @Override
    public String getCommandName() {
        return "/setblock";
    }

    @Override
    protected String getCommand() {
        String blockStateStr = getBlockState().toString();
        return String.format("/setblock ~ ~ ~ %s%s%s replace", BuiltInRegistries.BLOCK.getKey(getBlockState().getBlock()),
                getBlockState().getProperties().isEmpty() ? "" : blockStateStr.substring(blockStateStr.indexOf("[")), getTag());
    }
}
