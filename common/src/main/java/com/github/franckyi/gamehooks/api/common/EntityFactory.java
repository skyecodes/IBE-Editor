package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface EntityFactory {
    Entity fromTag(ObjectTag tag);
}
