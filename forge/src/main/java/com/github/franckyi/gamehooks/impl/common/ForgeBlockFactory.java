package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.BlockFactory;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public final class ForgeBlockFactory implements BlockFactory {
    public static final BlockFactory INSTANCE = new ForgeBlockFactory();

    @Override
    public Block from(ObjectTag tag) {
        return new ForgeBlock(tag);
    }
}
