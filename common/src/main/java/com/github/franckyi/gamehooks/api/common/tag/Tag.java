package com.github.franckyi.gamehooks.api.common.tag;

public interface Tag {
    byte BYTE_ID = 1;
    String BYTE_NAME = "TAG_Byte";
    byte SHORT_ID = 2;
    String SHORT_NAME = "TAG_Short";
    byte INT_ID = 3;
    String INT_NAME = "TAG_Int";
    byte LONG_ID = 4;
    String LONG_NAME = "TAG_Long";
    byte FLOAT_ID = 5;
    String FLOAT_NAME = "TAG_Float";
    byte DOUBLE_ID = 6;
    String DOUBLE_NAME = "TAG_Double";
    byte BYTE_ARRAY_ID = 7;
    String BYTE_ARRAY_NAME = "TAG_Byte_Array";
    byte STRING_ID = 8;
    String STRING_NAME = "TAG_String";
    byte LIST_ID = 9;
    String LIST_NAME = "TAG_List";
    byte COMPOUND_ID = 10;
    String COMPOUND_NAME = "TAG_Compound";
    byte INT_ARRAY_ID = 11;
    String INT_ARRAY_NAME = "TAG_Int_Array";
    byte LONG_ARRAY_ID = 12;
    String LONG_ARRAY_NAME = "TAG_Long_Array";

    byte getType();

    <T> T get();
}
