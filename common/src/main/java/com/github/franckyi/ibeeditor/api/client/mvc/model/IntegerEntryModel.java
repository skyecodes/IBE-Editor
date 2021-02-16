package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.ibeeditor.api.client.mvc.view.IntegerEntryView;

public interface IntegerEntryModel extends ValueEntryModel<Integer> {
    default int getIntValue() {
        return valueProperty().getValue();
    }

    @Override
    IntegerProperty valueProperty();

    default void setIntValue(int value) {
        valueProperty().setValue(value);
    }

    @Override
    default Class<? extends View> getDefaultViewType() {
        return IntegerEntryView.class;
    }
}
