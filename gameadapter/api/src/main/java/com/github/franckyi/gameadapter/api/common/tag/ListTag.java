package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.Collection;
import java.util.List;

public interface ListTag extends Tag, List<Tag> {
    static ListTag create() {
        return Game.getCommon().getTagFactory().createListTag();
    }

    static ListTag create(Collection<Tag> value) {
        return Game.getCommon().getTagFactory().createListTag(value);
    }

    @Override
    default byte getType() {
        return Tag.LIST_ID;
    }

    String getString(int index);

    CompoundTag getCompound(int index);

    void addString(String value);

    void addTag(Tag tag);
}
