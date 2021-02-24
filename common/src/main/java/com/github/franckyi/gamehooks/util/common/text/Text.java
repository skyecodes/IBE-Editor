package com.github.franckyi.gamehooks.util.common.text;

public final class Text {
    public static final Text EMPTY = literal("");
    private final String text;
    private final String link;
    private final boolean translated;
    private final TextStyle style;
    private final TextFormatting[] formatting;

    public Text(String text, String link, boolean translated, TextStyle style, TextFormatting[] formatting) {
        this.text = text;
        this.link = link;
        this.translated = translated;
        this.style = style;
        this.formatting = formatting;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
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
        return new Text(text, null, false, null, null);
    }

    public static Text literal(String text, TextStyle style) {
        return new Text(text, null, false, style, null);
    }

    public static Text literal(String text, TextFormatting... formatting) {
        return new Text(text, null, false, null, formatting);
    }

    public static Text literal(String text, String link) {
        return new Text(text, link, false, null, null);
    }

    public static Text literal(String text, String link, TextStyle style) {
        return new Text(text, link, false, style, null);
    }

    public static Text literal(String text, String link, TextFormatting... formatting) {
        return new Text(text, link, false, null, formatting);
    }

    public static Text translated(String text) {
        return new Text(text, null, true, null, null);
    }

    public static Text translated(String text, TextStyle style) {
        return new Text(text, null, true, style, null);
    }

    public static Text translated(String text, TextFormatting... formatting) {
        return new Text(text, null, true, null, formatting);
    }

    public static Text translated(String text, String link) {
        return new Text(text, link, true, null, null);
    }

    public static Text translated(String text, String link, TextStyle style) {
        return new Text(text, link, true, style, null);
    }

    public static Text translated(String text, String link, TextFormatting... formatting) {
        return new Text(text, link, true, null, formatting);
    }

    @Override
    public String toString() {
        return text;
    }
}
