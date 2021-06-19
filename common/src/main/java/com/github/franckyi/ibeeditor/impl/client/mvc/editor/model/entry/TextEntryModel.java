package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryType;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TextEntryModel extends ValueEntryModel<Text> {
    private final Text defaultTranslatedValue;
    private final StringProperty rawValueProperty;

    public TextEntryModel(CategoryModel category, Text label, Text value, Consumer<Text> action, Text defaultTranslatedValue) {
        super(category, label, value, action);
        this.defaultTranslatedValue = defaultTranslatedValue;
        rawValueProperty = DataBindings.getPropertyFactory().createStringProperty(value.getRawText());
        valueProperty().addListener(newVal -> setRawValue(newVal.getRawText()));
    }

    @Override
    public EntryType getType() {
        return EntryType.TEXT;
    }

    public String getRawValue() {
        return rawValueProperty().getValue();
    }

    public StringProperty rawValueProperty() {
        return rawValueProperty;
    }

    public void setRawValue(String value) {
        rawValueProperty().setValue(value);
    }

    public Text getValueForString(String string, int firstCharacterIndex) {
        return getValue();
    }

    public Text getDefaultTranslatedValue() {
        return defaultTranslatedValue;
    }
}
