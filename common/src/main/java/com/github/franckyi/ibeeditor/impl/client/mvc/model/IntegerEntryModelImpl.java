package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.model.IntegerEntryModel;

import java.util.function.Consumer;

public class IntegerEntryModelImpl extends AbstractValueEntryModel<Integer> implements IntegerEntryModel {
    public IntegerEntryModelImpl(Text label, int defaultValue, Consumer<Integer> apply) {
        super(label, defaultValue, PropertyFactory.ofInteger(defaultValue), apply);
    }

    @Override
    public IntegerProperty valueProperty() {
        return (IntegerProperty) super.valueProperty();
    }
}
