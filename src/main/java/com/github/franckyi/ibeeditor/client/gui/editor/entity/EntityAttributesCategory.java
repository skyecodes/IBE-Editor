package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyDouble;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;

public class EntityAttributesCategory extends AbstractCategory {

    private final LivingEntity entity;

    public EntityAttributesCategory(LivingEntity entity) {
        this.entity = entity;
        entity.getAttributeManager().getInstances().forEach(instance -> {
            Attribute a = instance.getAttribute();
            String name = a.getAttributeName();
            PropertyDouble p = new PropertyDouble(name, instance.getBaseValue(),
                    instance::setBaseValue, a.clampValue(Double.NEGATIVE_INFINITY), a.clampValue(Double.POSITIVE_INFINITY));
            p.getNameLabel().setPrefWidth(150);
            this.getChildren().add(p);
        });
    }
}
