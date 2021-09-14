package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.fabric.common.IFabricStyle;
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
    @Shadow
    @Final
    @Nullable TextColor color;

    @Shadow
    @Final
    @Nullable Boolean bold;

    @Shadow
    @Final
    @Nullable Boolean italic;

    @Shadow
    @Final
    @Nullable Boolean underlined;

    @Shadow
    @Final
    @Nullable Boolean strikethrough;

    @Shadow
    @Final
    @Nullable Boolean obfuscated;

    @Shadow
    @Final
    @Nullable ClickEvent clickEvent;

    @Shadow
    @Final
    @Nullable HoverEvent hoverEvent;

    @Shadow
    @Final
    @Nullable String insertion;

    @Shadow
    @Final
    @Nullable Identifier font;

    @Override
    public @Nullable Boolean getBold() {
        return bold;
    }

    @Override
    public @Nullable Boolean getItalic() {
        return italic;
    }

    @Override
    public @Nullable Boolean getUnderlined() {
        return underlined;
    }

    @Override
    public @Nullable Boolean getStrikethrough() {
        return strikethrough;
    }

    @Override
    public @Nullable Boolean getObfuscated() {
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
    public Style withUnderlineCommon(Boolean underlined) {
        return create(color, bold, italic, underlined, strikethrough, obfuscated, clickEvent, hoverEvent, insertion, font);
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
