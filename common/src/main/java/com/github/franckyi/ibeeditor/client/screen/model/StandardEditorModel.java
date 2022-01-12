package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

public abstract class StandardEditorModel<T, C extends CategoryModel> extends CategoryEntryScreenModel<C> {
    private final T target;
    private final Consumer<T> action;
    private final Component disabledTooltip;
    private final MutableComponent title;
    private final StringProperty currentCustomColorProperty;
    private final BooleanProperty saveToVaultProperty;
    private final boolean canSaveToVault;

    protected StandardEditorModel(T target, Consumer<T> action, Component disabledTooltip, MutableComponent title, boolean canSaveToVault) {
        this.target = target;
        this.action = action;
        this.disabledTooltip = disabledTooltip;
        this.title = title;
        currentCustomColorProperty = StringProperty.create("#ffffff");
        saveToVaultProperty = BooleanProperty.create(false);
        this.canSaveToVault = canSaveToVault;
    }

    public T getTarget() {
        return target;
    }

    public Component getDisabledTooltip() {
        return disabledTooltip;
    }

    public boolean isDisabled() {
        return getDisabledTooltip() != null;
    }

    public MutableComponent getTitle() {
        return title;
    }

    public String getCurrentCustomColor() {
        return currentCustomColorProperty().getValue();
    }

    public StringProperty currentCustomColorProperty() {
        return currentCustomColorProperty;
    }

    public void setCurrentCustomColor(String value) {
        currentCustomColorProperty().setValue(value);
    }

    @Override
    public void apply() {
        T model = applyChanges();
        action.accept(model);
        if (saveToVaultProperty().getValue()) {
            if (saveToVault(model)) {
                Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.successSavedVault(getTitle()), false);
            } else {
                Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.warnNotSavedVault(getTitle()), false);
            }
        }
    }

    public BooleanProperty saveToVaultProperty() {
        return saveToVaultProperty;
    }

    public boolean canSaveToVault() {
        return canSaveToVault;
    }

    protected abstract T applyChanges();

    protected abstract boolean saveToVault(T model);
}
