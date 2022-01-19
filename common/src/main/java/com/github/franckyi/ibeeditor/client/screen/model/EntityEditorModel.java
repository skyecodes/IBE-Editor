package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;

public class EntityEditorModel extends StandardEditorModel {
    public EntityEditorModel(EntityEditorContext context) {
        super(context);
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public MutableComponent getEditorName() {
        return ModTexts.ENTITY;
    }
}
