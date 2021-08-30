package com.github.franckyi.gameadapter.api.common.text;

import com.github.franckyi.gameadapter.Game;

import java.util.List;
import java.util.function.Consumer;

public interface IText {
    @SuppressWarnings("unchecked")
    static <T extends IText> T fromJson(String json) {
        return (T) Game.getCommon().createTextFromJson(json);
    }

    static IText empty() {
        return Game.getCommon().getEmptyText();
    }

    List<IText> getExtra();

    void addExtra(IText extra);

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

    ITextEvent getClickEvent();

    void setClickEvent(ITextEvent clickEvent);

    ITextEvent getHoverEvent();

    void setHoverEvent(ITextEvent hoverEvent);

    String getRawText();

    String toJson();

    IText copy();

    default <T extends IText> T color(String color) {
        return with(t -> t.setColor(color));
    }

    default <T extends IText> T black() {
        return color(Color.BLACK);
    }

    default <T extends IText> T darkBlue() {
        return color(Color.DARK_BLUE);
    }

    default <T extends IText> T darkGreen() {
        return color(Color.DARK_GREEN);
    }

    default <T extends IText> T darkAqua() {
        return color(Color.DARK_AQUA);
    }

    default <T extends IText> T darkRed() {
        return color(Color.DARK_RED);
    }

    default <T extends IText> T darkPurple() {
        return color(Color.DARK_PURPLE);
    }

    default <T extends IText> T gold() {
        return color(Color.GOLD);
    }

    default <T extends IText> T gray() {
        return color(Color.GRAY);
    }

    default <T extends IText> T darkGray() {
        return color(Color.DARK_GRAY);
    }

    default <T extends IText> T blue() {
        return color(Color.BLUE);
    }

    default <T extends IText> T green() {
        return color(Color.GREEN);
    }

    default <T extends IText> T aqua() {
        return color(Color.AQUA);
    }

    default <T extends IText> T red() {
        return color(Color.RED);
    }

    default <T extends IText> T lightPurple() {
        return color(Color.LIGHT_PURPLE);
    }

    default <T extends IText> T yellow() {
        return color(Color.YELLOW);
    }

    default <T extends IText> T white() {
        return color(Color.WHITE);
    }

    default <T extends IText> T bold(boolean bold) {
        return with(t -> t.setBold(bold));
    }

    default <T extends IText> T bold() {
        return bold(true);
    }

    default <T extends IText> T italic(boolean italic) {
        return with(t -> t.setItalic(italic));
    }

    default <T extends IText> T italic() {
        return italic(true);
    }

    default <T extends IText> T underlined(boolean underlined) {
        return with(t -> t.setUnderlined(underlined));
    }

    default <T extends IText> T underlined() {
        return underlined(true);
    }

    default <T extends IText> T strikethrough(boolean strikethrough) {
        return with(t -> t.setStrikethrough(strikethrough));
    }

    default <T extends IText> T strikethrough() {
        return strikethrough(true);
    }

    default <T extends IText> T obfuscated(boolean obfuscated) {
        return with(t -> t.setObfuscated(obfuscated));
    }

    default <T extends IText> T obfuscated() {
        return obfuscated(true);
    }

    default <T extends IText> T click(ITextEvent clickEvent) {
        return with(t -> t.setClickEvent(clickEvent));
    }

    @Deprecated // not implemented into mixin
    default <T extends IText> T hover(ITextEvent hoverEvent) {
        return with(t -> t.setHoverEvent(hoverEvent));
    }

    default <T extends IText> T extra(IText... extra) {
        return with(t -> {
            for (IText e : extra) {
                t.addExtra(e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    default <T extends IText> T getThis() {
        return (T) this;
    }

    default <T extends IText> T with(Consumer<T> with) {
        with.accept(getThis());
        return getThis();
    }

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













