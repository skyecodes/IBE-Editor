package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.entity.AbstractEntityEditorCategoryModel;

import java.util.function.Consumer;

public class EntityEditorModel extends AbstractStandardEditorModel<Entity, AbstractEntityEditorCategoryModel> {
    public EntityEditorModel(Entity entity, Consumer<Entity> action, Text disabledTooltip) {
        super(entity, action, disabledTooltip, "ibeeditor.text.entity");
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public Entity applyChanges() {
        return getTarget(); // TODO
    }
}
