package com.github.franckyi.gamehooks.util.common.tag;

import java.util.HashMap;
import java.util.Map;

public class ObjectTag extends AbstractTag<Map<String, Tag<?>>> {
    public ObjectTag() {
        this(new HashMap<>());
    }

    public ObjectTag(Map<String, Tag<?>> value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 8;
    }
}
