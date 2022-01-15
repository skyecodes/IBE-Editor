package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.common.EditorContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.TagParser;

public class SNBTEditorModel implements EditorModel {
    private final EditorContext context;
    private final StringProperty valueProperty;

    public SNBTEditorModel(EditorContext context) {
        this.context = context;
        valueProperty = StringProperty.create(context.getTag().toString());
    }

    public String getValue() {
        return valueProperty().getValue();
    }

    public StringProperty valueProperty() {
        return valueProperty;
    }

    public void setValue(String value) {
        valueProperty().setValue(value);
    }

    public void apply() {
        try {
            context.setTag(TagParser.parseTag(getValue()));
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EditorContext getContext() {
        return context;
    }

    @Override
    public boolean saveToVault() {
        return false;
    }
}
