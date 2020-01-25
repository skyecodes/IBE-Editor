package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.EntityIcons;
import com.github.franckyi.ibeeditor.client.IBENotification;
import com.github.franckyi.ibeeditor.client.gui.editor.base.CapabilityProviderEditor;
import com.github.franckyi.ibeeditor.common.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.common.network.editor.entity.EntityEditorMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;

public class EntityEditor extends CapabilityProviderEditor {

    private final Entity entity;
    private final Consumer<Entity> action;

    public EntityEditor(Entity entity) {
        this(entity, null);
    }

    public EntityEditor(Entity entity, Consumer<Entity> action) {
        super("Entity Editor :");
        this.entity = entity;
        this.action = action;
        header.getChildren().add(new TexturedButton(
                EntityIcons.getHeadFromEntityType(entity.getType()), entity.getName().getFormattedText()));
        this.addCategory("General", new GeneralEntityCategory(entity));
        if (!(entity instanceof PlayerEntity)) {
            //this.applyConfigurations(this.getCapabilityConfigurations(), entity); // NOT READY
            if (entity instanceof LivingEntity) {
                this.addCategory("Inventory", new InventoryEntityCategory((LivingEntity) entity));
                if (entity instanceof MobEntity) {
                    this.addCategory("Loot", new LootEntityCategory((MobEntity) entity));
                }
            }
            this.addCategory("Tools", new ToolsEntityCategory(entity));
        }
        this.show();
    }

    @Override
    protected void apply() {
        CompoundNBT entityTag = entity.writeWithoutTypeId(new CompoundNBT());
        super.apply();
        CompoundNBT newTag = entity.writeWithoutTypeId(new CompoundNBT());
        if (entityTag.equals(newTag)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.YELLOW + "Nothing to save.");
        } else {
            if (action != null) {
                action.accept(entity);
            } else {
                IBENetworkHandler.getModChannel().sendToServer(new EntityEditorMessage(entity));
                IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Entity saved.");
            }
        }
    }
}
