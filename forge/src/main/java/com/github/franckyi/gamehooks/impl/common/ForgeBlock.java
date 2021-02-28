package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public class ForgeBlock implements Block {
    private final ObjectTag state;
    private final ObjectTag data;

    public ForgeBlock(ObjectTag state, ObjectTag data) {
        this.state = state;
        this.data = data;
    }

    @Override
    public ObjectTag getState() {
        return state;
    }

    @Override
    public ObjectTag getData() {
        return data;
    }
}
