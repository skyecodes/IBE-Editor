package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.Map;
import java.util.UUID;

public interface CompoundTag extends Tag, Map<String, Tag> {
    static CompoundTag create() {
        return Game.getCommon().getTagFactory().createCompoundTag();
    }

    static CompoundTag create(Map<String, Tag> value) {
        return Game.getCommon().getTagFactory().createCompoundTag(value);
    }

    static CompoundTag parse(String snbt) {
        return Game.getCommon().getTagFactory().parseCompoundTag(snbt);
    }

    @Override
    default byte getType() {
        return Tag.COMPOUND_ID;
    }

    int getInt(String key);

    String getString(String key);

    boolean getBoolean(String key);

    double getDouble(String key);

    UUID getUUID(String key);

    ListTag getList(String key, byte type);

    ListTag getOrCreateList(String key, byte type);

    CompoundTag getCompound(String key);

    CompoundTag getOrCreateCompound(String key);

    void putString(String key, String value);

    void putInt(String key, int value);

    void putBoolean(String key, boolean value);

    void putDouble(String key, double value);

    void putUUID(String key, UUID value);

    void putTag(String key, Tag tag);

    boolean contains(String key, byte type);

    void remove(String key);

    boolean isEmpty();

    CompoundTag copy();

    class CompoundTagEntry implements Entry<String, Tag> {
        private final String key;
        private Tag value;

        public CompoundTagEntry(String key, Tag value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Tag getValue() {
            return value;
        }

        @Override
        public Tag setValue(Tag value) {
            Tag old = this.value;
            this.value = value;
            return old;
        }
    }
}
