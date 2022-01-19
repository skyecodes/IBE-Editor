package com.github.franckyi.ibeeditor.common;

public enum EditorType {
    STANDARD, NBT, SNBT;

    @Deprecated
    public boolean isNBT() {
        return this != STANDARD;
    }
}
