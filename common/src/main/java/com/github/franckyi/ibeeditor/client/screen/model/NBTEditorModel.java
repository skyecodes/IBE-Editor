package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class NBTEditorModel implements Model {
    private final ObjectProperty<NBTTagModel> rootTagProperty;
    private final ObjectProperty<NBTTagModel> clipboardTagProperty = ObjectProperty.create();
    private final Consumer<CompoundTag> action;
    private final Component disabledTooltip;
    private final BooleanProperty saveToVaultProperty;
    private final boolean canSaveToVault;

    public NBTEditorModel(CompoundTag tag, Consumer<CompoundTag> action, Component disabledTooltip, boolean canSaveToVault) {
        rootTagProperty = ObjectProperty.create(new NBTTagModel(tag));
        this.action = action;
        this.disabledTooltip = disabledTooltip;
        saveToVaultProperty = BooleanProperty.create(false);
        this.canSaveToVault = canSaveToVault;
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

    public Component getDisabledTooltip() {
        return disabledTooltip;
    }

    public boolean canSave() {
        return getDisabledTooltip() == null;
    }

    public BooleanProperty saveToVaultProperty() {
        return saveToVaultProperty;
    }

    public boolean canSaveToVault() {
        return canSaveToVault;
    }

    public void apply() {
        action.accept((CompoundTag) getRootTag().build());
    }
}
