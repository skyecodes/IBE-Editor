package com.github.franckyi.ibeeditor.client.context;

import com.github.franckyi.ibeeditor.client.ClientUtil;
import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class EntityEditorContext extends EditorContext<EntityEditorContext> {
    public EntityEditorContext(CompoundTag tag, Component errorTooltip, boolean canSaveToVault, Consumer<EntityEditorContext> action) {
        super(tag, errorTooltip, canSaveToVault, action);
    }

    @Override
    public void saveToVault() {
        Vault.getInstance().saveEntity(getTag());
        ClientUtil.showMessage(ModTexts.Messages.successSavedVault(ModTexts.ENTITY));
    }
}
