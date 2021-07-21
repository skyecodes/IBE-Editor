package com.github.franckyi.gameadapter.base.common.text;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.TextEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private TextEvent clickEvent;
    private TextEvent hoverEvent;

    protected AbstractText() {
    }

    protected AbstractText(Text text) {
        extra = text.getExtra();
        color = text.getColor();
        bold = text.getBold();
        italic = text.getItalic();
        underlined = text.getUnderlined();
        strikethrough = text.getStrikethrough();
        obfuscated = text.getObfuscated();
        clickEvent = text.getClickEvent();
        hoverEvent = text.getHoverEvent();
    }

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
    public void addExtra(Text extra) {
        if (getExtra() == null) {
            setExtra(new ArrayList<>());
        }
        getExtra().add(extra);
        shouldUpdateComponent = true;
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
    public TextEvent getClickEvent() {
        return clickEvent;
    }

    @Override
    public void setClickEvent(TextEvent clickEvent) {
        if (!Objects.equals(this.clickEvent, clickEvent)) {
            this.clickEvent = clickEvent;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public TextEvent getHoverEvent() {
        return hoverEvent;
    }

    @Override
    public void setHoverEvent(TextEvent hoverEvent) {
        if (!Objects.equals(this.hoverEvent, hoverEvent)) {
            this.hoverEvent = hoverEvent;
            shouldUpdateComponent = true;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get() {
        if (shouldUpdateComponent || component == null) {
            component = Game.getCommon().getTextComponentFactory().createComponentFromText(this);
        }
        return (T) component;
    }

    @Override
    public String getRawText() {
        return getExtra() == null ? "" : getExtra().stream().map(Text::getRawText).collect(Collectors.joining());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractText that = (AbstractText) o;
        return Objects.equals(extra, that.extra) && Objects.equals(color, that.color) && Objects.equals(bold, that.bold) && Objects.equals(italic, that.italic) && Objects.equals(underlined, that.underlined) && Objects.equals(strikethrough, that.strikethrough) && Objects.equals(obfuscated, that.obfuscated) && Objects.equals(clickEvent, that.clickEvent) && Objects.equals(hoverEvent, that.hoverEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extra, color, bold, italic, underlined, strikethrough, obfuscated, clickEvent, hoverEvent);
    }
}
