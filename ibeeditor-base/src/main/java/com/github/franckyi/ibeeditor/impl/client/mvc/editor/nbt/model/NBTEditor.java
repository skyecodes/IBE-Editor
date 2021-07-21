package com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.EditorTagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.NBTEditorModel;

import java.util.function.Consumer;

public class NBTEditor implements NBTEditorModel {
    private final ObjectProperty<EditorTagModel> rootTagProperty;
    private final ObjectProperty<EditorTagModel> clipboardTagProperty = DataBindings.getPropertyFactory().createObjectProperty();
    private final Consumer<CompoundTag> action;
    private final Text disabledTooltip;

    public NBTEditor(CompoundTag tag, Consumer<CompoundTag> action, Text disabledTooltip) {
        rootTagProperty = DataBindings.getPropertyFactory().createObjectProperty(new EditorTagModelImpl(tag));
        this.action = action;
        this.disabledTooltip = disabledTooltip;
    }

    @Override
    public ObjectProperty<EditorTagModel> rootTagProperty() {
        return rootTagProperty;
    }

    @Override
    public ObjectProperty<EditorTagModel> clipboardTagProperty() {
        return clipboardTagProperty;
    }

    @Override
    public Text getDisabledTooltip() {
        return disabledTooltip;
    }

    @Override
    public void apply() {
        action.accept((CompoundTag) getRootTag().build());
    }
}
