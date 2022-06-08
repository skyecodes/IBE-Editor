package com.github.franckyi.ibeeditor.client.context;

import com.github.franckyi.ibeeditor.client.ClientUtil;
import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Consumer;

public class EntityEditorContext extends EditorContext<EntityEditorContext> {
    private final Entity entity;

    public EntityEditorContext(CompoundTag tag, Component errorTooltip, boolean canSaveToVault, Consumer<EntityEditorContext> action) {
        super(tag, errorTooltip, canSaveToVault, action);
        entity = EntityType.create(tag, Minecraft.getInstance().level).orElse(null);
        if (entity == null) {
            this.canSaveToVault = false;
        }
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public void saveToVault() {
        Vault.getInstance().saveEntity(getTag());
        ClientUtil.showMessage(ModTexts.Messages.successSavedVault(ModTexts.ENTITY));
    }

    @Override
    public MutableComponent getTargetName() {
        return ModTexts.ENTITY;
    }

    @Override
    public String getCommandName() {
        return "/summon";
    }

    @Override
    protected String getCommand() {
        return String.format("/summon %s ~ ~ ~ %s", getTag().getString("id"), getSimpleTag());
    }

    private CompoundTag getSimpleTag() {
        CompoundTag tag = getTag().copy();
        tag.remove("UUID");
        tag.remove("Pos");
        tag.remove("Rotation");
        return tag;
    }
}
