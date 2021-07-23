package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.mvc.Model;

import java.util.function.Consumer;

public class NBTEditorModel implements Model {
    private final ObjectProperty<EditorTagModel> rootTagProperty;
    private final ObjectProperty<EditorTagModel> clipboardTagProperty = DataBindings.getPropertyFactory().createObjectProperty();
    private final Consumer<CompoundTag> action;
    private final Text disabledTooltip;

    public NBTEditorModel(CompoundTag tag, Consumer<CompoundTag> action, Text disabledTooltip) {
        rootTagProperty = DataBindings.getPropertyFactory().createObjectProperty(new EditorTagModel(tag));
        this.action = action;
        this.disabledTooltip = disabledTooltip;
    }

    public EditorTagModel getRootTag() {
        return rootTagProperty().getValue();
    }

    public ObjectProperty<EditorTagModel> rootTagProperty() {
        return rootTagProperty;
    }

    public void setRootTag(EditorTagModel value) {
        rootTagProperty().setValue(value);
    }

    public EditorTagModel getClipboardTag() {
        return clipboardTagProperty().getValue();
    }

    public ObjectProperty<EditorTagModel> clipboardTagProperty() {
        return clipboardTagProperty;
    }

    public void setClipboardTag(EditorTagModel value) {
        clipboardTagProperty().setValue(value);
    }

    public boolean canSave() {
        return getDisabledTooltip() == null;
    }

    public Text getDisabledTooltip() {
        return disabledTooltip;
    }

    public void apply() {
        action.accept((CompoundTag) getRootTag().build());
    }
}
