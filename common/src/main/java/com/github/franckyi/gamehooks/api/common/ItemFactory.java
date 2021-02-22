package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface ItemFactory {
    Item fromTag(ObjectTag tag);
}
