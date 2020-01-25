package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyInteger;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.StringTextComponent;

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
                new PropertyInteger("Fire", entity.getFireTimer(), entity::setFireTimer)
        );
    }

    private void setName(String s) {
        if (s.isEmpty()) {
            entity.setCustomName(null);
        } else {
            entity.setCustomName(new StringTextComponent(s));
        }
    }

}
