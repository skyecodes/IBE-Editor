package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public class FabricEntity implements Entity {
    private final ObjectTag tag;

    public FabricEntity(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        return tag;
    }
}
