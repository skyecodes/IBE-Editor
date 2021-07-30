package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public abstract class LabeledEntryModel extends EntryModel {
    private final ObjectProperty<Text> labelProperty;

    protected LabeledEntryModel(CategoryModel category, Text label) {
        super(category);
        labelProperty = ObjectProperty.create(label);
    }

    public Text getLabel() {
        return labelProperty().getValue();
    }

    public ObjectProperty<Text> labelProperty() {
        return labelProperty;
    }

    public void setLabel(Text value) {
        labelProperty().setValue(value);
    }
}
