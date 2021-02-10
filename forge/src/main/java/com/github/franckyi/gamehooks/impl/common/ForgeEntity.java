package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;

public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }
}
