package com.github.franckyi.gamehooks.api.common.text;

public final class Text {
    public static final Text EMPTY = literal("");
    private final String text;
    private final boolean translated;
    private final TextStyle style;
    private final TextFormatting[] formatting;

    public Text(String text, boolean translated, TextStyle style, TextFormatting[] formatting) {
        this.text = text;
        this.translated = translated;
        this.style = style;
        this.formatting = formatting;
    }

    public String getText() {
        return text;
    }

    public boolean isTranslated() {
        return translated;
    }

    public TextStyle getStyle() {
        return style;
    }

    public TextFormatting[] getFormatting() {
        return formatting;
    }

    public static Text literal(String text) {
        return new Text(text, false, null, null);
    }

    public static Text literal(String text, TextStyle style) {
        return new Text(text, false, style, null);
    }

    public static Text literal(String text, TextFormatting... formatting) {
        return new Text(text, false, null, formatting);
    }

    public static Text translated(String text) {
        return new Text(text, true, null, null);
    }

    public static Text translated(String text, TextStyle style) {
        return new Text(text, true, style, null);
    }

    public static Text translated(String text, TextFormatting... formatting) {
        return new Text(text, true, null, formatting);
    }
}
