package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;

public abstract class LabeledEditorEntryModel extends EditorEntryModel {
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
