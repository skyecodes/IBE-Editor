package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public abstract class LabeledEntryModel extends EntryModel {
    private final ObjectProperty<IText> labelProperty;
    private final IntegerProperty labeledContentWeightProperty;

    protected LabeledEntryModel(CategoryModel category, IText label) {
        super(category);
        labelProperty = ObjectProperty.create(label);
        labeledContentWeightProperty = IntegerProperty.create(1);
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

    public int getLabelWeight() {
        return labeledContentWeightProperty().getValue();
    }

    public IntegerProperty labeledContentWeightProperty() {
        return labeledContentWeightProperty;
    }

    @SuppressWarnings("unchecked")
    public <T extends LabeledEntryModel> T withWeight(int value) {
        labeledContentWeightProperty().setValue(value);
        return (T) this;
    }
}
