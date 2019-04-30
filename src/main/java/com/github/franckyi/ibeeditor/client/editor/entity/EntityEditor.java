package com.github.franckyi.ibeeditor.client.editor.entity;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.editor.common.CapabilityProviderEditor;
import com.github.franckyi.ibeeditor.client.util.EntityIcons;
import com.github.franckyi.ibeeditor.client.util.IBENotification;
import com.github.franckyi.ibeeditor.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.network.entity.EntityEditorMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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
        if (!(entity instanceof EntityPlayer)) {
            //this.applyConfigurations(this.getCapabilityConfigurations(), entity); // NOT READY
            if (entity instanceof EntityLiving) {
                this.addCategory("Inventory", new InventoryEntityCategory((EntityLiving) entity));
            }
            this.addCategory("Tools", new ToolsEntityCategory(entity));
        }
        this.show();
    }

    @Override
    protected void apply() {
        NBTTagCompound entityTag = entity.writeWithoutTypeId(new NBTTagCompound());
        super.apply();
        NBTTagCompound newTag = entity.writeWithoutTypeId(new NBTTagCompound());
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
