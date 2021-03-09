package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TextFactory;
import com.github.franckyi.minecraft.util.common.TextStyle;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;

import java.util.stream.Stream;

public final class ForgeTextFactory implements TextFactory {
    public static final TextFactory INSTANCE = new ForgeTextFactory();

    private ForgeTextFactory() {
    }

    @Override
    public Text create(String text, String url, boolean translated, TextStyle style, com.github.franckyi.minecraft.util.common.TextFormatting[] formatting) {
        if (text == null) return empty();
        IFormattableTextComponent t;
        if (translated) {
            t = new TranslationTextComponent(text);
        } else {
            t = new StringTextComponent(text);
        }
        if (formatting != null && formatting.length > 0) {
            net.minecraft.util.text.TextFormatting[] f = Stream.of(formatting)
                    .map(item -> {
                        switch (item) {
                            case BLACK:
                                return TextFormatting.BLACK;
                            case DARK_BLUE:
                                return TextFormatting.DARK_BLUE;
                            case DARK_GREEN:
                                return TextFormatting.DARK_GREEN;
                            case DARK_AQUA:
                                return TextFormatting.DARK_AQUA;
                            case DARK_RED:
                                return TextFormatting.DARK_RED;
                            case DARK_PURPLE:
                                return TextFormatting.DARK_PURPLE;
                            case GOLD:
                                return TextFormatting.GOLD;
                            case GRAY:
                                return TextFormatting.GRAY;
                            case DARK_GRAY:
                                return TextFormatting.DARK_GRAY;
                            case BLUE:
                                return TextFormatting.BLUE;
                            case GREEN:
                                return TextFormatting.GREEN;
                            case AQUA:
                                return TextFormatting.AQUA;
                            case RED:
                                return TextFormatting.RED;
                            case LIGHT_PURPLE:
                                return TextFormatting.LIGHT_PURPLE;
                            case YELLOW:
                                return TextFormatting.YELLOW;
                            case WHITE:
                                return TextFormatting.WHITE;
                            case OBFUSCATED:
                                return TextFormatting.OBFUSCATED;
                            case BOLD:
                                return TextFormatting.BOLD;
                            case STRIKETHROUGH:
                                return TextFormatting.STRIKETHROUGH;
                            case UNDERLINE:
                                return TextFormatting.UNDERLINE;
                            case ITALIC:
                                return TextFormatting.ITALIC;
                            case RESET:
                                return TextFormatting.RESET;
                        }
                        return null;
                    }).toArray(TextFormatting[]::new);
            t.modifyStyle(s -> s.createStyleFromFormattings(f));
        }
        if (style != null) {
            if (style.getColor() != null) {
                t.modifyStyle(s -> s.setColor(Color.fromInt(style.getColor())));
            }
            if (style.getBold() != null) {
                t.modifyStyle(s -> s.setBold(style.getBold()));
            }
            if (style.getItalic() != null) {
                t.modifyStyle(s -> s.setItalic(style.getItalic()));
            }
            if (style.getUnderline() != null) {
                t.modifyStyle(s -> s.setUnderlined(style.getUnderline()));
            }
        }
        if (url != null) {
            t.modifyStyle(s -> s.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)));
        }
        return new ForgeText(t);
    }

    @Override
    public Text empty() {
        return ForgeText.EMPTY;
    }
}
