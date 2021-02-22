package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.BlockFactory;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public final class FabricBlockFactory implements BlockFactory {
    public static final BlockFactory INSTANCE = new FabricBlockFactory();

    private FabricBlockFactory() {
    }

    @Override
    public Block from(ObjectTag tag) {
        return new FabricBlock(tag);
    }
}
