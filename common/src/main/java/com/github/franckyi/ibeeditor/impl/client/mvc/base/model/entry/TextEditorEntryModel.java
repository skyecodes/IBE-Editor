package com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TextEditorEntryModel extends ValueEditorEntryModel<Text> {
    private final Text defaultTranslatedValue;
    private final Text customTextRoot;
    private final StringProperty rawValueProperty;

    public TextEditorEntryModel(EditorCategoryModel category, Text label, Text value, Consumer<Text> action, Text defaultTranslatedValue, Text customTextRoot) {
        super(category, label, value, action);
        this.defaultTranslatedValue = defaultTranslatedValue;
        this.customTextRoot = customTextRoot;
        customTextRoot.setExtra(new ArrayList<>());
        rawValueProperty = DataBindings.getPropertyFactory().createStringProperty(value.getRawText());
        valueProperty().addListener(newVal -> setRawValue(newVal.getRawText()));
    }

    @Override
    public Type getType() {
        return Type.TEXT;
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

    public void resetValue() {
        setValue(defaultTranslatedValue);
    }

    public void initValue(Text text) {
        customTextRoot.getExtra().clear();
        customTextRoot.getExtra().add(text);
        setValue(customTextRoot);
    }
}
