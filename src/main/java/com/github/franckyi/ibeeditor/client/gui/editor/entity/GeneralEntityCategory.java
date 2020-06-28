package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyFloat;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyInteger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.StringTextComponent;

public class GeneralEntityCategory extends AbstractCategory {

    private final Entity entity;
    private PropertyFloat healthProperty;

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
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            this.getChildren().add(0, healthProperty = new PropertyFloat("Health", livingEntity.getHealth(),
                    livingEntity::setHealth, 0, livingEntity.getMaxHealth()));
        }
    }

    private void setName(String s) {
        if (s.isEmpty()) {
            entity.setCustomName(null);
        } else {
            entity.setCustomName(new StringTextComponent(s));
        }
    }

    @Override
    public void apply() {
        super.apply();
        if (healthProperty != null) {
            healthProperty.getNumberField().setMax(((LivingEntity) entity).getMaxHealth());
        }
    }
}
