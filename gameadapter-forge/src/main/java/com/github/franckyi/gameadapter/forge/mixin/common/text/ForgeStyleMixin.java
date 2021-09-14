package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.forge.common.IForgeStyle;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import javax.annotation.Nullable;

@Mixin(Style.class)
public abstract class ForgeStyleMixin implements IForgeStyle {
    @Shadow
    @Final
    @Nullable
    TextColor color;

    @Shadow
    @Final
    @Nullable
    Boolean bold;

    @Shadow
    @Final
    @Nullable
    Boolean italic;

    @Shadow
    @Final
    @Nullable
    Boolean underlined;

    @Shadow
    @Final
    @Nullable
    Boolean strikethrough;

    @Shadow
    @Final
    @Nullable
    Boolean obfuscated;

    @Shadow
    @Final
    @Nullable
    ClickEvent clickEvent;

    @Shadow
    @Final
    @Nullable
    HoverEvent hoverEvent;

    @Shadow
    @Final
    @Nullable
    String insertion;

    @Shadow
    @Final
    @Nullable
    ResourceLocation font;

    @Nullable
    @Override
    public Boolean getBold() {
        return bold;
    }

    @Nullable
    @Override
    public Boolean getItalic() {
        return italic;
    }

    @Nullable
    @Override
    public Boolean getUnderlined() {
        return underlined;
    }

    @Nullable
    @Override
    public Boolean getStrikethrough() {
        return strikethrough;
    }

    @Nullable
    @Override
    public Boolean getObfuscated() {
        return obfuscated;
    }

    @Invoker("<init>")
    static Style create(@Nullable TextColor color, @Nullable Boolean bold,
                        @Nullable Boolean italic, @Nullable Boolean underlined,
                        @Nullable Boolean strikethrough, @Nullable Boolean obfuscated,
                        @Nullable ClickEvent clickEvent, @Nullable HoverEvent hoverEvent,
                        @Nullable String insertion, @Nullable ResourceLocation font) {
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
