package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.github.franckyi.ibeeditor.api.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;

public class NBTEditorModelImpl implements NBTEditorModel {
    private final ObjectProperty<TagModel> tagProperty;

    public NBTEditorModelImpl(ObjectTag tag) {
        tagProperty = PropertyFactory.ofObject(new TagModelImpl(tag));
    }

    @Override
    public ObjectProperty<TagModel> tagProperty() {
        return tagProperty;
    }
}
