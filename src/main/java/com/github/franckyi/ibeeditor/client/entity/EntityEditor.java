package com.github.franckyi.ibeeditor.client.entity;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.AbstractEditor;
import com.github.franckyi.ibeeditor.network.entity.EntityEditorMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class EntityEditor extends AbstractEditor {

    private final Entity entity;

    public EntityEditor(Entity entity) {
        super("Entity Editor");
        this.entity = entity;
        this.addCategory("General", new GeneralEntityCategory(entity));
        if (!(entity instanceof EntityPlayer)) {
            if (entity instanceof EntityLiving) {
                this.addCategory("Inventory", new InventoryEntityCategory((EntityLiving) entity));
            }
            this.addCategory("Tools", new ToolsEntityCategory(entity));
        }
        this.show();
    }

    @Override
    protected void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(new EntityEditorMessage(entity));
    }
}
