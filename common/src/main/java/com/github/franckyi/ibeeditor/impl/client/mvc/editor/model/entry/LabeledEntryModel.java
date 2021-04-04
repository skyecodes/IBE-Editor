package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.minecraft.api.common.text.Text;

public abstract class LabeledEntryModel extends AbstractEntryModel {
    private final ObjectProperty<Text> labelProperty;

    protected LabeledEntryModel(CategoryModel category, Text label) {
        super(category);
        labelProperty = Bindings.getPropertyFactory().ofObject(label);
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
