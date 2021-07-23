package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.EntityEditorCategoryModel;

import java.util.function.Consumer;

public class EntityEditorModel extends StandardEditorModel<Entity, EntityEditorCategoryModel> {
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
