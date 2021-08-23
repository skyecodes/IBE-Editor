package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITextEvent;
import com.github.franckyi.gameadapter.api.internal.IStyle;
import com.github.franckyi.gameadapter.forge.common.IForgeStyle;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(TextComponent.class)
public abstract class ForgeTextComponentMixin implements IFormattableTextComponent, IText {
    @Shadow
    public abstract List<ITextComponent> getSiblings();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<IText> getExtra() {
        return (List) getSiblings();
    }

    @Override
    public void addExtra(IText extra) {
        append((ITextComponent) extra);
    }

    @Override
    public String getColor() {
        net.minecraft.util.text.Color color = getStyle().getColor();
        return color == null ? null : color.serialize();
    }

    @Override
    public void setColor(String color) {
        withStyle(style -> style.withColor(net.minecraft.util.text.Color.parseColor(color)));
    }

    @Override
    public Boolean getBold() {
        return ((IStyle) getStyle()).getBold();
    }

    @Override
    public void setBold(Boolean bold) {
        withStyle(style -> style.withBold(bold));
    }

    @Override
    public Boolean getItalic() {
        return ((IStyle) getStyle()).getItalic();
    }

    @Override
    public void setItalic(Boolean italic) {
        withStyle(style -> style.withItalic(italic));
    }

    @Override
    public Boolean getUnderlined() {
        return ((IStyle) getStyle()).getUnderlined();
    }

    @Override
    public void setUnderlined(Boolean underlined) {
        withStyle(style -> style.withUnderlined(underlined));
    }

    @Override
    public Boolean getStrikethrough() {
        return ((IStyle) getStyle()).getStrikethrough();
    }

    @Override
    public void setStrikethrough(Boolean strikethrough) {
        withStyle(style -> ((IForgeStyle) style).withStrikethrough(strikethrough));
    }

    @Override
    public Boolean getObfuscated() {
        return ((IStyle) getStyle()).getObfuscated();
    }

    @Override
    public void setObfuscated(Boolean obfuscated) {
        withStyle(style -> ((IForgeStyle) style).withObfuscated(obfuscated));
    }

    @Override
    public ITextEvent getClickEvent() {
        return (ITextEvent) getStyle().getClickEvent();
    }

    @Override
    public void setClickEvent(ITextEvent clickEvent) {
        withStyle(style -> style.withClickEvent((ClickEvent) clickEvent));
    }

    @Override
    public ITextEvent getHoverEvent() {
        return (ITextEvent) getStyle().getHoverEvent();
    }

    @Override
    public void setHoverEvent(ITextEvent hoverEvent) {
        withStyle(style -> style.withHoverEvent((HoverEvent) hoverEvent));
    }

    @Override
    public String getRawText() {
        return getString();
    }

    @Override
    public String toJson() {
        return ITextComponent.Serializer.toJson(this);
    }
}
