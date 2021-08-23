package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Style.class)
public abstract class FabricStyleMixin implements IFabricStyle {
    @Shadow @Final @Nullable private TextColor color;

    @Shadow @Final @Nullable private Boolean bold;

    @Shadow @Final @Nullable private Boolean italic;

    @Shadow @Final @Nullable private Boolean underlined;

    @Shadow @Final @Nullable private Boolean strikethrough;

    @Shadow @Final @Nullable private Boolean obfuscated;

    @Shadow @Final @Nullable private ClickEvent clickEvent;

    @Shadow @Final @Nullable private HoverEvent hoverEvent;

    @Shadow @Final @Nullable private String insertion;

    @Shadow @Final @Nullable private Identifier font;

    @Override
    public Boolean getBold() {
        return bold;
    }

    @Override
    public Boolean getItalic() {
        return italic;
    }

    @Override
    public Boolean getUnderlined() {
        return underlined;
    }

    @Override
    public Boolean getStrikethrough() {
        return strikethrough;
    }

    @Override
    public Boolean getObfuscated() {
        return obfuscated;
    }

    @Invoker("<init>")
    static Style create(@Nullable TextColor color, @Nullable Boolean bold,
                        @Nullable Boolean italic, @Nullable Boolean underlined,
                        @Nullable Boolean strikethrough, @Nullable Boolean obfuscated,
                        @Nullable ClickEvent clickEvent, @Nullable HoverEvent hoverEvent,
                        @Nullable String insertion, @Nullable Identifier font) {
        throw new AssertionError();
    }

    @Override
    public Style withStrikethrough(Boolean strikethrough) {
        return create(color, bold, italic, underlined, strikethrough, obfuscated, clickEvent, hoverEvent, insertion, font);
    }

    @Override
    public Style withObfuscated(Boolean obfuscated) {
        return create(color, bold, italic, underlined, strikethrough, obfuscated, clickEvent, hoverEvent, insertion, font);
    }
}
