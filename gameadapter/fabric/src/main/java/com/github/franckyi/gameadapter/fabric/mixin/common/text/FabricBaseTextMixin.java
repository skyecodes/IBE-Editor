package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITextEvent;
import com.github.franckyi.gameadapter.api.internal.IStyle;
import net.minecraft.text.*;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(BaseText.class)
public abstract class FabricBaseTextMixin implements MutableText, IText {
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<IText> getExtra() {
        return (List) getSiblings();
    }

    @Override
    public void addExtra(IText extra) {
        append((Text) extra);
    }

    @Override
    public String getColor() {
        TextColor color = getStyle().getColor();
        return color == null ? null : color.getName();
    }

    @Override
    public void setColor(String color) {
        styled(style -> style.withColor(TextColor.parse(color)));
    }

    @Override
    public Boolean getBold() {
        return ((IStyle) getStyle()).getBold();
    }

    @Override
    public void setBold(Boolean bold) {
        styled(style -> style.withBold(bold));
    }

    @Override
    public Boolean getItalic() {
        return ((IStyle) getStyle()).getItalic();
    }

    @Override
    public void setItalic(Boolean italic) {
        styled(style -> style.withItalic(italic));
    }

    @Override
    public Boolean getUnderlined() {
        return ((IStyle) getStyle()).getUnderlined();
    }

    @Override
    public void setUnderlined(Boolean underlined) {
        styled(style -> style.withUnderline(underlined));
    }

    @Override
    public Boolean getStrikethrough() {
        return ((IStyle) getStyle()).getStrikethrough();
    }

    @Override
    public void setStrikethrough(Boolean strikethrough) {
        styled(style -> ((IFabricStyle) style).withStrikethrough(strikethrough));
    }

    @Override
    public Boolean getObfuscated() {
        return ((IStyle) getStyle()).getObfuscated();
    }

    @Override
    public void setObfuscated(Boolean obfuscated) {
        styled(style -> ((IFabricStyle) style).withObfuscated(obfuscated));
    }

    @Override
    public ITextEvent getClickEvent() {
        return (ITextEvent) getStyle().getClickEvent();
    }

    @Override
    public void setClickEvent(ITextEvent clickEvent) {
        styled(style -> style.withClickEvent((ClickEvent) clickEvent));
    }

    @Override
    public ITextEvent getHoverEvent() {
        return (ITextEvent) getStyle().getHoverEvent();
    }

    @Override
    public void setHoverEvent(ITextEvent hoverEvent) {
        styled(style -> style.withHoverEvent((HoverEvent) hoverEvent));
    }

    @Override
    public String getRawText() {
        return getString();
    }

    @Override
    public String toJson() {
        return Text.Serializer.toJson(this);
    }
}
