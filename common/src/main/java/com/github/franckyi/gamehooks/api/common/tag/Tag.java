package com.github.franckyi.gamehooks.api.common.tag;

public interface Tag {
    byte BYTE_ID = 1;
    byte SHORT_ID = 2;
    byte INT_ID = 3;
    byte LONG_ID = 4;
    byte FLOAT_ID = 5;
    byte DOUBLE_ID = 6;
    byte BYTE_ARRAY_ID = 7;
    byte STRING_ID = 8;
    byte LIST_ID = 9;
    byte COMPOUND_ID = 10;
    byte INT_ARRAY_ID = 11;
    byte LONG_ARRAY_ID = 12;

    byte getType();

    <T> T get();
}
