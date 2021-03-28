package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Entity;

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
