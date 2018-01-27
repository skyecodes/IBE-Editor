package com.github.franckyi.ibeeditor.util;

public class IBEUtil {

    public static String unformatString(String s) {
        return s.startsWith("§r") ? s.substring(2) : s;
    }

    public static String formatString(String s) {
        return s.startsWith("§r") ? s : String.format("§r%s", s);
    }

}
