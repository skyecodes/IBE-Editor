package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

public class NBTEditor implements NBTEditorModel {
    private final ObjectProperty<TagModel> rootTagProperty;
    private final ObjectProperty<TagModel> clipboardTagProperty = DataBindings.getPropertyFactory().createObjectProperty();
    private final Consumer<CompoundTag> action;
    private final Text disabledTooltip;

    public NBTEditor(CompoundTag tag, Consumer<CompoundTag> action, Text disabledTooltip) {
        rootTagProperty = DataBindings.getPropertyFactory().createObjectProperty(new TagModelImpl(tag));
        this.action = action;
        this.disabledTooltip = disabledTooltip;
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
    public Text getDisabledTooltip() {
        return disabledTooltip;
    }

    @Override
    public void apply() {
        action.accept((CompoundTag) getRootTag().build());
    }
}
