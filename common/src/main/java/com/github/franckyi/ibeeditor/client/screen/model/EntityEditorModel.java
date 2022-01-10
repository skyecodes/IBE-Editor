package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.screen.model.category.entity.EntityCategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class EntityEditorModel extends StandardEditorModel<CompoundTag, EntityCategoryModel> {
    public EntityEditorModel(CompoundTag entity, Consumer<CompoundTag> action, Component disabledTooltip) {
        super(entity, action, disabledTooltip, ModTexts.ENTITY);
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public CompoundTag applyChanges() {
        return getTarget(); // TODO
    }
}
