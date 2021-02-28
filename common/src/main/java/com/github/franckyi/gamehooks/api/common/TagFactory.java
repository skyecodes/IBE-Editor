package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.api.common.tag.Tag;

public interface TagFactory {
    Tag create(byte type);
}
