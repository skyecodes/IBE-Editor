package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.List;
import java.util.Objects;

public abstract class AbstractText implements Text {
    protected transient boolean shouldUpdateComponent = true;
    protected transient Object component;
    private List<Text> extra;
    private String color;
    private Boolean bold;
    private Boolean italic;
    private Boolean underlined;
    private Boolean strikethrough;
    private Boolean obfuscated;
    private Event clickEvent;
    private Event hoverEvent;

    @Override
    public List<Text> getExtra() {
        return extra;
    }

    @Override
    public void setExtra(List<Text> extra) {
        if (!Objects.equals(this.extra, extra)) {
            this.extra = extra;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        if (!Objects.equals(this.color, color)) {
            this.color = color;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public Boolean getBold() {
        return bold;
    }

    @Override
    public void setBold(Boolean bold) {
        if (!Objects.equals(this.bold, bold)) {
            this.bold = bold;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public Boolean getItalic() {
        return italic;
    }

    @Override
    public void setItalic(Boolean italic) {
        if (!Objects.equals(this.italic, italic)) {
            this.italic = italic;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public Boolean getUnderlined() {
        return underlined;
    }

    @Override
    public void setUnderlined(Boolean underlined) {
        if (!Objects.equals(this.underlined, underlined)) {
            this.underlined = underlined;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public Boolean getStrikethrough() {
        return strikethrough;
    }

    @Override
    public void setStrikethrough(Boolean strikethrough) {
        if (!Objects.equals(this.strikethrough, strikethrough)) {
            this.strikethrough = strikethrough;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public Boolean getObfuscated() {
        return obfuscated;
    }

    @Override
    public void setObfuscated(Boolean obfuscated) {
        if (!Objects.equals(this.obfuscated, obfuscated)) {
            this.obfuscated = obfuscated;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public Event getClickEvent() {
        return clickEvent;
    }

    @Override
    public void setClickEvent(Event clickEvent) {
        if (!Objects.equals(this.clickEvent, clickEvent)) {
            this.clickEvent = clickEvent;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public Event getHoverEvent() {
        return hoverEvent;
    }

    @Override
    public void setHoverEvent(Event hoverEvent) {
        if (!Objects.equals(this.hoverEvent, hoverEvent)) {
            this.hoverEvent = hoverEvent;
            shouldUpdateComponent = true;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get() {
        if (shouldUpdateComponent || component == null) {
            component = Minecraft.getCommon().getTextFactory().createComponentFromText(this);
        }
        return (T) component;
    }

    @Override
    public String getRawText() {
        return Minecraft.getCommon().getTextFactory().getRawTextFromComponent(get());
    }
}
