package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

public abstract class LabeledEntryModel extends EntryModel {
    private final ObjectProperty<MutableComponent> labelProperty;
    private final IntegerProperty labeledContentWeightProperty;

    protected LabeledEntryModel(CategoryModel category, MutableComponent label) {
        super(category);
        labelProperty = ObjectProperty.create(label);
        labeledContentWeightProperty = IntegerProperty.create(1);
    }

    public MutableComponent getLabel() {
        return labelProperty().getValue();
    }

    public ObjectProperty<MutableComponent> labelProperty() {
        return labelProperty;
    }

    public void setLabel(MutableComponent value) {
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
