package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.github.franckyi.ibeeditor.api.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;

import java.util.function.Consumer;

public class NBTEditorModelImpl implements NBTEditorModel {
    private final ObjectProperty<TagModel> tagProperty;
    private final Consumer<ObjectTag> action;

    public NBTEditorModelImpl(ObjectTag tag, Consumer<ObjectTag> action) {
        tagProperty = PropertyFactory.ofObject(new TagModelImpl(tag));
        this.action = action;
    }

    @Override
    public ObjectProperty<TagModel> tagProperty() {
        return tagProperty;
    }

    @Override
    public void apply() {
        action.accept((ObjectTag) getTag().build());
    }
}
