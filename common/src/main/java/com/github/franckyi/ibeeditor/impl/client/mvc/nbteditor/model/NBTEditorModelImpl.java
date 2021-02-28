package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.model;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;

import java.util.function.Consumer;

public class NBTEditorModelImpl implements NBTEditorModel {
    private final ObjectProperty<TagModel> rootTagProperty;
    private final ObjectProperty<TagModel> clipboardTagProperty = PropertyFactory.ofObject();
    private final Consumer<CompoundTag> action;

    public NBTEditorModelImpl(CompoundTag tag, Consumer<CompoundTag> action) {
        rootTagProperty = PropertyFactory.ofObject(new TagModelImpl(tag));
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
