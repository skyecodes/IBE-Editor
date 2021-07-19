package com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.minecraft.api.common.text.Text;

public abstract class LabeledEditorEntryModel extends AbstractEditorEntryModel {
    private final ObjectProperty<Text> labelProperty;

    protected LabeledEditorEntryModel(EditorCategoryModel category, Text label) {
        super(category);
        labelProperty = DataBindings.getPropertyFactory().createObjectProperty(label);
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
