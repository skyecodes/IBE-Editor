package com.github.franckyi.gamehooks.util.common.tag;

public class StringTag extends AbstractTag<String> {
    public StringTag() {
        this("");
    }

    public StringTag(String value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 8;
    }
}
