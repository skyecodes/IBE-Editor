package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.model;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;

import java.util.function.Consumer;

public class NBTEditor implements NBTEditorModel {
    private final ObjectProperty<TagModel> rootTagProperty;
    private final ObjectProperty<TagModel> clipboardTagProperty = Bindings.getPropertyFactory().ofObject();
    private final Consumer<CompoundTag> action;

    public NBTEditor(CompoundTag tag, Consumer<CompoundTag> action) {
        rootTagProperty = Bindings.getPropertyFactory().ofObject(new TagModelImpl(tag));
        this.action = action;
    }

    @Override
    public ObjectProperty<TagModel> rootTagProperty() {
        return rootTagProperty;
    }

    @Override
    public ObjectProperty<TagModel> clipboardTagProperty() {
        return clipboardTagProperty;
    }

    @Override
    public void apply() {
        action.accept((CompoundTag) getRootTag().build());
    }
}
