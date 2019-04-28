package com.github.franckyi.ibeeditor.client.editor.entity;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.editor.common.AbstractCategory;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyInteger;
import com.github.franckyi.ibeeditor.network.entity.EntityEditorMessage;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;

public class GeneralEntityCategory extends AbstractCategory {

    private final Entity entity;

    public GeneralEntityCategory(Entity entity) {
        this.entity = entity;
        this.addAll(
                new PropertyFormattedText("Name", entity.hasCustomName() ?
                        entity.getCustomName().getFormattedText() : "", this::setName),
                new PropertyBoolean("Show name", entity.isCustomNameVisible(), entity::setCustomNameVisible),
                new PropertyBoolean("Invisible", entity.isInvisible(), entity::setInvisible),
                new PropertyBoolean("Invulnerable", entity.isInvulnerable(), entity::setInvulnerable),
                new PropertyBoolean("Silent", entity.isSilent(), entity::setSilent),
                new PropertyBoolean("No gravity", entity.hasNoGravity(), entity::setNoGravity),
                new PropertyBoolean("Glowing", entity.isGlowing(), entity::setGlowing),
                new PropertyInteger("Fire", (int) entity.getEntityData().getShort("Fire"),
                        i -> entity.setFire(i / 20), Short.MIN_VALUE, Short.MAX_VALUE)
        );
    }

    private void setName(String s) {
        if (s.isEmpty()) {
            entity.setCustomName(null);
        } else {
            entity.setCustomName(new TextComponentString(s));
        }
    }

    @Override
    public void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(new EntityEditorMessage(entity));
    }
}
