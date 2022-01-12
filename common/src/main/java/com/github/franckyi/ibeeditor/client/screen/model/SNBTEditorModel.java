package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class SNBTEditorModel implements Model {
    private final StringProperty valueProperty;
    private final Consumer<CompoundTag> action;
    private final Component disabledTooltip;
    private final BooleanProperty saveToVaultProperty;
    private final boolean canSaveToVault;

    public SNBTEditorModel(CompoundTag value, Consumer<CompoundTag> action, Component disabledTooltip, boolean canSaveToVault) {
        valueProperty = StringProperty.create(value.toString());
        this.action = action;
        this.disabledTooltip = disabledTooltip;
        saveToVaultProperty = BooleanProperty.create(false);
        this.canSaveToVault = canSaveToVault;
    }

    public String getValue() {
        return valueProperty().getValue();
    }

    public StringProperty valueProperty() {
        return valueProperty;
    }

    public void setValue(String value) {
        valueProperty().setValue(value);
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
        try {
            action.accept(TagParser.parseTag(getValue()));
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
