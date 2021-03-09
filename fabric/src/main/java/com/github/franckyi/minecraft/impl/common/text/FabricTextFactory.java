package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TextFactory;
import com.github.franckyi.minecraft.util.common.TextFormatting;
import com.github.franckyi.minecraft.util.common.TextStyle;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.stream.Stream;

public class FabricTextFactory implements TextFactory {
    public static final TextFactory INSTANCE = new FabricTextFactory();

    private FabricTextFactory() {
    }

    @Override
    public Text create(String text, String url, boolean translated, TextStyle style, TextFormatting[] formatting) {
        if (text == null) return empty();
        MutableText t;
        if (translated) {
            t = new TranslatableText(text);
        } else {
            t = new LiteralText(text);
        }
        if (formatting != null && formatting.length > 0) {
            Formatting[] f = Stream.of(formatting)
                    .map(item -> {
                        switch (item) {
                            case BLACK:
                                return Formatting.BLACK;
                            case DARK_BLUE:
                                return Formatting.DARK_BLUE;
                            case DARK_GREEN:
                                return Formatting.DARK_GREEN;
                            case DARK_AQUA:
                                return Formatting.DARK_AQUA;
                            case DARK_RED:
                                return Formatting.DARK_RED;
                            case DARK_PURPLE:
                                return Formatting.DARK_PURPLE;
                            case GOLD:
                                return Formatting.GOLD;
                            case GRAY:
                                return Formatting.GRAY;
                            case DARK_GRAY:
                                return Formatting.DARK_GRAY;
                            case BLUE:
                                return Formatting.BLUE;
                            case GREEN:
                                return Formatting.GREEN;
                            case AQUA:
                                return Formatting.AQUA;
                            case RED:
                                return Formatting.RED;
                            case LIGHT_PURPLE:
                                return Formatting.LIGHT_PURPLE;
                            case YELLOW:
                                return Formatting.YELLOW;
                            case WHITE:
                                return Formatting.WHITE;
                            case OBFUSCATED:
                                return Formatting.OBFUSCATED;
                            case BOLD:
                                return Formatting.BOLD;
                            case STRIKETHROUGH:
                                return Formatting.STRIKETHROUGH;
                            case UNDERLINE:
                                return Formatting.UNDERLINE;
                            case ITALIC:
                                return Formatting.ITALIC;
                            case RESET:
                                return Formatting.RESET;
                        }
                        return null;
                    }).toArray(Formatting[]::new);
            t.styled(s -> s.withFormatting(f));
        }
        if (style != null) {
            if (style.getColor() != null) {
                t.styled(s -> s.withColor(TextColor.fromRgb(style.getColor())));
            }
            if (style.getBold() != null) {
                t.styled(s -> s.withBold(style.getBold()));
            }
            if (style.getItalic() != null) {
                t.styled(s -> s.withItalic(style.getItalic()));
            }
            if (style.getUnderline() != null) {
                t.styled(s -> s.withUnderline(style.getUnderline()));
            }
        }
        if (url != null) {
            t.styled(s -> s.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)));
        }
        return new FabricText(t);
    }

    @Override
    public Text empty() {
        return FabricText.EMPTY;
    }
}
