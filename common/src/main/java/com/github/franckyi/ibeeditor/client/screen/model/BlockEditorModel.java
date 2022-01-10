package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.screen.model.category.block.BlockCategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Consumer;

public class BlockEditorModel extends StandardEditorModel<Pair<BlockState, CompoundTag>, BlockCategoryModel> {
    public BlockEditorModel(Pair<BlockState, CompoundTag> block, Consumer<Pair<BlockState, CompoundTag>> action, Component disabledTooltip) {
        super(block, action, disabledTooltip, ModTexts.BLOCK);
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public Pair<BlockState, CompoundTag> applyChanges() {
        return getTarget(); // TODO
    }
}
