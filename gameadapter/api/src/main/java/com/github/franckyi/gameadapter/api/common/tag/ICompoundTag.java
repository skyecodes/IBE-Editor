package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.Map;
import java.util.UUID;

public interface ICompoundTag extends ITag, Map<String, ITag> {
    static ICompoundTag create() {
        return Game.getCommon().getTagFactory().createCompoundTag();
    }

    static ICompoundTag create(Map<String, ITag> value) {
        return Game.getCommon().getTagFactory().createCompoundTag(value);
    }

    static ICompoundTag parse(String snbt) {
        return Game.getCommon().getTagFactory().parseCompoundTag(snbt);
    }

    int getInt(String key);

    String getString(String key);

    boolean getBoolean(String key);

    double getDouble(String key);

    UUID getUUID(String key);

    IListTag getList(String key, byte type);

    ICompoundTag getCompound(String key);

    default ICompoundTag getOrCreateCompound(String key) {
        if (!contains(key, COMPOUND_ID)) {
            put(key, ICompoundTag.create());
        }
        return getCompound(key);
    }

    void putString(String key, String value);

    void putInt(String key, int value);

    void putBoolean(String key, boolean value);

    void putDouble(String key, double value);

    void putUUID(String key, UUID value);

    void putTag(String key, ITag tag);

    boolean contains(String key, byte type);

    void remove(String key);

    boolean isEmpty();

    ICompoundTag copy();
}
