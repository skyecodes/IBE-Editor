package com.github.franckyi.ibeeditor.base.common;

public enum EditorType {
    STANDARD(0), NBT(1), RAW_NBT(2);

    private final byte id;

    EditorType(int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return id;
    }

    public boolean isNBT() {
        return this != STANDARD;
    }

    public static EditorType from(byte id) {
        switch (id) {
            case 0: return STANDARD;
            case 1: return NBT;
            case 2: return RAW_NBT;
            default: return null;
        }
    }
}
