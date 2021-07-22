package com.github.franckyi.ibeeditor.base.client.mvc.base.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;

public abstract class LabeledEditorEntryModel extends AbstractEditorEntryModel {
    private final ObjectProperty<Text> labelProperty;
    private final IntegerProperty labelWeightProperty;

    protected LabeledEditorEntryModel(EditorCategoryModel category, Text label) {
        super(category);
        labelProperty = DataBindings.getPropertyFactory().createObjectProperty(label);
        labelWeightProperty = DataBindings.getPropertyFactory().createIntegerProperty(1);
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

    public int getLabelWeight() {
        return labelWeightProperty().getValue();
    }

    public IntegerProperty labelWeightProperty() {
        return labelWeightProperty;
    }

    public void setLabelWeight(int value) {
        labelWeightProperty().setValue(value);
    }

    public LabeledEditorEntryModel withLabelWeight(int value) {
        setLabelWeight(value);
        return this;
    }
}
