package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.model.StringEntryModel;

import java.util.function.Consumer;

public class StringEntryModelImpl extends AbstractValueEntryModel<String> implements StringEntryModel {
    public StringEntryModelImpl(Text label, String defaultValue, Consumer<String> apply) {
        super(label, defaultValue, PropertyFactory.ofString(defaultValue), apply);
    }

    @Override
    public StringProperty valueProperty() {
        return (StringProperty) super.valueProperty();
    }
}
