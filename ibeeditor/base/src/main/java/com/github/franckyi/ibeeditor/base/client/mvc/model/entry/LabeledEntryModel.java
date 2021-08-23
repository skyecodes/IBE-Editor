package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public abstract class LabeledEntryModel extends EntryModel {
    private final ObjectProperty<IText> labelProperty;

    protected LabeledEntryModel(CategoryModel category, IText label) {
        super(category);
        labelProperty = ObjectProperty.create(label);
    }

    public IText getLabel() {
        return labelProperty().getValue();
    }

    public ObjectProperty<IText> labelProperty() {
        return labelProperty;
    }

    public void setLabel(IText value) {
        labelProperty().setValue(value);
    }
}
