package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.editor;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.entity.AbstractEntityCategoryModel;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

public class EntityEditorModel extends AbstractEditorModel<Entity, AbstractEntityCategoryModel> {
    public EntityEditorModel(Entity entity, Consumer<Entity> action, Text disabledTooltip) {
        super(entity, action, disabledTooltip);
    }

    @Override
    public Entity applyChanges() {
        return getTarget(); // TODO
    }
}
