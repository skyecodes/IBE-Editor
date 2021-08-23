package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.mvc.Model;

import java.util.function.Consumer;

public class NBTEditorModel implements Model {
    private final ObjectProperty<NBTTagModel> rootTagProperty;
    private final ObjectProperty<NBTTagModel> clipboardTagProperty = ObjectProperty.create();
    private final Consumer<ICompoundTag> action;
    private final IText disabledTooltip;

    public NBTEditorModel(ICompoundTag tag, Consumer<ICompoundTag> action, IText disabledTooltip) {
        rootTagProperty = ObjectProperty.create(new NBTTagModel(tag));
        this.action = action;
        this.disabledTooltip = disabledTooltip;
    }

    public NBTTagModel getRootTag() {
        return rootTagProperty().getValue();
    }

    public ObjectProperty<NBTTagModel> rootTagProperty() {
        return rootTagProperty;
    }

    public void setRootTag(NBTTagModel value) {
        rootTagProperty().setValue(value);
    }

    public NBTTagModel getClipboardTag() {
        return clipboardTagProperty().getValue();
    }

    public ObjectProperty<NBTTagModel> clipboardTagProperty() {
        return clipboardTagProperty;
    }

    public void setClipboardTag(NBTTagModel value) {
        clipboardTagProperty().setValue(value);
    }

    public boolean canSave() {
        return getDisabledTooltip() == null;
    }

    public IText getDisabledTooltip() {
        return disabledTooltip;
    }

    public void apply() {
        action.accept((ICompoundTag) getRootTag().build());
    }
}
