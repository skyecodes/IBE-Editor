package com.github.franckyi.ibeeditor.client.entity;

import com.github.franckyi.ibeeditor.client.AbstractEditor;
import net.minecraft.entity.Entity;

public class EntityEditor extends AbstractEditor {

    private final Entity entity;

    public EntityEditor(Entity entity) {
        this.entity = entity;
        this.addCategory("General", new GeneralEntityCategory(entity));
        this.show();
    }
}
