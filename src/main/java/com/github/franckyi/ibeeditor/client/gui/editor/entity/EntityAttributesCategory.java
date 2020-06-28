package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyDouble;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class EntityAttributesCategory extends AbstractCategory {

    private final LivingEntity entity;

    public EntityAttributesCategory(LivingEntity entity) {
        this.entity = entity;
        entity.getAttributes().getAllAttributes().forEach(instance -> {
            IAttribute a = instance.getAttribute();
            String name;
            if (a instanceof RangedAttribute) {
                RangedAttribute ra = (RangedAttribute) a;
                if (ra.getDescription() == null) {
                    name = ra.getName();
                } else {
                    name = ra.getDescription();
                }
            } else {
                name = a.getName();
            }
            PropertyDouble p = new PropertyDouble(name, instance.getBaseValue(),
                    instance::setBaseValue, a.clampValue(Double.NEGATIVE_INFINITY), a.clampValue(Double.POSITIVE_INFINITY));
            p.getNameLabel().setPrefWidth(150);
            this.getChildren().add(p);
        });
    }
}
