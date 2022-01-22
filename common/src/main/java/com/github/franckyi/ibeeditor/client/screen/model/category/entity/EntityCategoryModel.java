package com.github.franckyi.ibeeditor.client.screen.model.category.entity;

import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.client.screen.model.EntityEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.EditorCategoryModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

public abstract class EntityCategoryModel extends EditorCategoryModel {
    public EntityCategoryModel(Component name, EntityEditorModel editor) {
        super(name, editor);
    }

    @Override
    public EntityEditorContext getContext() {
        return (EntityEditorContext) super.getContext();
    }

    protected CompoundTag getData() {
        return getContext().getTag();
    }

    protected Entity getEntity() {
        return getContext().getEntity();
    }
}
