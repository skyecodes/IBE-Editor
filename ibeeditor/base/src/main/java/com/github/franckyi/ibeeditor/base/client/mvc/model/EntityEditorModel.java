package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.EntityCategoryModel;

import java.util.function.Consumer;

public class EntityEditorModel extends StandardEditorModel<ICompoundTag, EntityCategoryModel> {
    public EntityEditorModel(ICompoundTag entity, Consumer<ICompoundTag> action, IText disabledTooltip) {
        super(entity, action, disabledTooltip, "ibeeditor.text.entity");
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public ICompoundTag applyChanges() {
        return getTarget(); // TODO
    }
}
