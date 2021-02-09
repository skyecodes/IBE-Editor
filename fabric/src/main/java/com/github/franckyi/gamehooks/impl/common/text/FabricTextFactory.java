package com.github.franckyi.gamehooks.impl.common.text;

import com.github.franckyi.gamehooks.api.common.text.TextFactory;
import com.github.franckyi.gamehooks.api.common.text.Text;
import com.github.franckyi.gamehooks.api.common.text.TextStyle;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.stream.Stream;

public class FabricTextFactory implements TextFactory<net.minecraft.text.Text> {
    public static final TextFactory<?> INSTANCE = new FabricTextFactory();

    private FabricTextFactory() {
    }

    @Override
    public net.minecraft.text.Text create(Text text) {
        if (text == null) return LiteralText.EMPTY;
        MutableText t;
        if (text.isTranslated()) {
            t = new TranslatableText(text.getText());
        } else {
            t = new LiteralText(text.getText());
        }
        if (text.getFormatting() != null && text.getFormatting().length > 0) {
            Formatting[] f = Stream.of(text.getFormatting())
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
        TextStyle textStyle = text.getStyle();
        if (textStyle != null) {
            if (textStyle.getColor() != null) {
                t.styled(s -> s.withColor(TextColor.fromRgb(textStyle.getColor())));
            }
            if (textStyle.getBold() != null) {
                t.styled(s -> s.withBold(textStyle.getBold()));
            }
            if (textStyle.getItalic() != null) {
                t.styled(s -> s.withItalic(textStyle.getItalic()));
            }
            if (textStyle.getUnderline() != null) {
                t.styled(s -> s.withUnderline(textStyle.getUnderline()));
            }
        }
        return t;
    }
}
