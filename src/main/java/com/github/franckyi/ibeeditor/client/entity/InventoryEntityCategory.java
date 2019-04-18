package com.github.franckyi.ibeeditor.client.entity;

import com.github.franckyi.ibeeditor.client.Category;
import net.minecraft.entity.EntityLiving;

public class InventoryEntityCategory extends Category {

    private EntityLiving entity;

    public InventoryEntityCategory(EntityLiving entity) {
        this.entity = entity;
        this.addAll(
        );
    }
}
