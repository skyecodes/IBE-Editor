package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.forge.common.IForgeStyle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
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
    private Color color;

    @Shadow
    @Final
    @Nullable
    private Boolean bold;

    @Shadow
    @Final
    @Nullable
    private Boolean italic;

    @Shadow
    @Final
    @Nullable
    private Boolean underlined;

    @Shadow
    @Final
    @Nullable
    private Boolean strikethrough;

    @Shadow
    @Final
    @Nullable
    private Boolean obfuscated;

    @Shadow
    @Final
    @Nullable
    private ClickEvent clickEvent;

    @Shadow
    @Final
    @Nullable
    private HoverEvent hoverEvent;

    @Shadow
    @Final
    @Nullable
    private String insertion;

    @Shadow
    @Final
    @Nullable
    private ResourceLocation font;

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
    static Style create(@Nullable Color color, @Nullable Boolean bold,
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
