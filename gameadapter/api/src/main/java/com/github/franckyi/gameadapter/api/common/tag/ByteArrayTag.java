package com.github.franckyi.gameadapter.api.common.tag;

public interface ByteArrayTag extends Tag {
    @Override
    default byte getType() {
        return Tag.BYTE_ARRAY_ID;
    }

    byte[] getValue();
}
