package com.github.franckyi.ibeeditor.client.context;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

public abstract class EditorContext<T extends EditorContext<T>> {
    private CompoundTag tag;
    protected Component errorTooltip;
    protected boolean canSaveToVault;
    private boolean isSaveToVault = false;
    private final Consumer<T> action;

    public EditorContext(CompoundTag tag, Component errorTooltip, boolean canSaveToVault, Consumer<T> action) {
        this.tag = tag;
        this.errorTooltip = errorTooltip;
        this.canSaveToVault = canSaveToVault;
        this.action = action;
    }

    public CompoundTag getTag() {
        return tag;
    }

    public void setTag(CompoundTag tag) {
        this.tag = tag;
    }

    public Component getErrorTooltip() {
        return errorTooltip;
    }

    public boolean hasPermission() {
        return errorTooltip == null;
    }

    @SuppressWarnings("unchecked")
    public void update() {
        if (isSaveToVault()) {
            saveToVault();
        }
        if (hasPermission()) {
            action.accept((T) this);
        }
    }

    public boolean canSaveToVault() {
        return canSaveToVault;
    }

    public boolean isSaveToVault() {
        return isSaveToVault;
    }

    public void setSaveToVault(boolean isSaveToVault) {
        this.isSaveToVault = isSaveToVault;
    }

    public void saveToVault() {
    }

    public abstract MutableComponent getTargetName();
}
