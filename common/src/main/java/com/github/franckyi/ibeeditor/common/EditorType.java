package com.github.franckyi.ibeeditor.common;

public enum EditorType {
    STANDARD(0), NBT(1), SNBT(2);

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
        return switch (id) {
            case 0 -> STANDARD;
            case 1 -> NBT;
            case 2 -> SNBT;
            default -> null;
        };
    }
}
