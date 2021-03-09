package com.github.franckyi.minecraft.util.common;

public final class TextStyle {
    public static final TextStyle EMPTY = new TextStyle(null, null, null, null);
    public static final TextStyle BOLD = new TextStyle(null, true, null, null);
    public static final TextStyle ITALIC = new TextStyle(null, null, true, null);
    public static final TextStyle UNDERLINE = new TextStyle(null, null, null, true);
    private final Integer color;
    private final Boolean bold;
    private final Boolean italic;
    private final Boolean underline;

    public TextStyle(Integer color, Boolean bold, Boolean italic, Boolean underline) {
        this.color = color;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
    }

    public Integer getColor() {
        return color;
    }

    public Boolean getBold() {
        return bold;
    }

    public Boolean getItalic() {
        return italic;
    }

    public Boolean getUnderline() {
        return underline;
    }
}
