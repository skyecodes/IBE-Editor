package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.TextFactory;
import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.gamehooks.util.common.text.TextStyle;
import net.minecraft.util.text.*;

import java.util.stream.Stream;

public final class ForgeTextFactory implements TextFactory<ITextComponent> {
    public static final TextFactory<ITextComponent> INSTANCE = new ForgeTextFactory();

    private ForgeTextFactory() {
    }

    @Override
    public ITextComponent create(Text text) {
        if (text == null) return StringTextComponent.EMPTY;
        IFormattableTextComponent t;
        if (text.isTranslated()) {
            t = new TranslationTextComponent(text.getText());
        } else {
            t = new StringTextComponent(text.getText());
        }
        com.github.franckyi.gamehooks.util.common.text.TextFormatting[] formatting = text.getFormatting();
        if (formatting != null && formatting.length > 0) {
            TextFormatting[] f = Stream.of(formatting)
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
        TextStyle textStyle = text.getStyle();
        if (textStyle != null) {
            if (textStyle.getColor() != null) {
                t.modifyStyle(s -> s.setColor(Color.fromInt(textStyle.getColor())));
            }
            if (textStyle.getBold() != null) {
                t.modifyStyle(s -> s.setBold(textStyle.getBold()));
            }
            if (textStyle.getItalic() != null) {
                t.modifyStyle(s -> s.setItalic(textStyle.getItalic()));
            }
            if (textStyle.getUnderline() != null) {
                t.modifyStyle(s -> s.setUnderlined(textStyle.getUnderline()));
            }
        }
        return t;
    }

    @Override
    public ITextComponent create(Text root, Text... siblings) {
        IFormattableTextComponent t = (IFormattableTextComponent) create(root);
        for (Text text1 : siblings) {
            t.append(create(text1));
        }
        return t;
    }

    @Override
    public Text from(ITextComponent text) {
        String s = text.getString();
        boolean translated = text instanceof TranslationTextComponent;
        TextStyle style = new TextStyle(text.getStyle().getColor() == null ? null : text.getStyle().getColor().getColor(),
                text.getStyle().getBold(), text.getStyle().getItalic(), text.getStyle().getUnderlined());
        return new Text(s, translated, style, null);
    }
}
