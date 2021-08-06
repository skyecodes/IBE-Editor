package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.Collection;
import java.util.List;

public interface IListTag extends ITag, List<ITag> {
    static IListTag create() {
        return Game.getCommon().getTagFactory().createListTag();
    }

    static IListTag create(Collection<ITag> value) {
        return Game.getCommon().getTagFactory().createListTag(value);
    }

    String getString(int index);

    ICompoundTag getCompound(int index);

    void addString(String value);

    void addTag(ITag tag);
}
