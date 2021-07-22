package com.github.franckyi.gameadapter.api.common.text;

import java.util.List;

public interface Text {
    List<Text> getExtra();

    void setExtra(List<Text> extra);

    void addExtra(Text extra);

    String getColor();

    void setColor(String color);

    default boolean isBold() {
        return getBold() != null && getBold();
    }

    Boolean getBold();

    void setBold(Boolean bold);

    default boolean isItalic() {
        return getItalic() != null && getItalic();
    }

    Boolean getItalic();

    void setItalic(Boolean italic);

    default boolean isUnderlined() {
        return getUnderlined() != null && getUnderlined();
    }

    Boolean getUnderlined();

    void setUnderlined(Boolean underlined);

    default boolean isStrikethrough() {
        return getStrikethrough() != null && getStrikethrough();
    }

    Boolean getStrikethrough();

    void setStrikethrough(Boolean strikethrough);

    default boolean isObfuscated() {
        return getObfuscated() != null && getObfuscated();
    }

    Boolean getObfuscated();

    void setObfuscated(Boolean obfuscated);

    TextEvent getClickEvent();

    void setClickEvent(TextEvent clickEvent);

    TextEvent getHoverEvent();

    void setHoverEvent(TextEvent hoverEvent);

    <T> T get();

    String getRawText();

    final class Color {
        public static final String BLACK = "black";
        public static final String DARK_BLUE = "dark_blue";
        public static final String DARK_GREEN = "dark_green";
        public static final String DARK_AQUA = "dark_aqua";
        public static final String DARK_RED = "dark_red";
        public static final String DARK_PURPLE = "dark_purple";
        public static final String GOLD = "gold";
        public static final String GRAY = "gray";
        public static final String DARK_GRAY = "dark_gray";
        public static final String BLUE = "blue";
        public static final String GREEN = "green";
        public static final String AQUA = "aqua";
        public static final String RED = "red";
        public static final String LIGHT_PURPLE = "light_purple";
        public static final String YELLOW = "yellow";
        public static final String WHITE = "white";
    }
}













