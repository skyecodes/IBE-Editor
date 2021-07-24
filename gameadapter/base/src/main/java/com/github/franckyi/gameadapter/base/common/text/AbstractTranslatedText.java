package com.github.franckyi.gameadapter.base.common.text;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.TranslatedText;

import java.util.List;
import java.util.Objects;

public abstract class AbstractTranslatedText extends AbstractText implements TranslatedText {
    private String translate;
    private List<Text> with;

    protected AbstractTranslatedText() {
    }

    protected AbstractTranslatedText(String translate) {
        this.translate = translate;
    }

    protected AbstractTranslatedText(TranslatedText text) {
        super(text);
        translate = text.getTranslate();
        with = text.getWith();
    }

    @Override
    public String getTranslate() {
        return translate;
    }

    @Override
    public void setTranslate(String translate) {
        if (!Objects.equals(this.translate, translate)) {
            this.translate = translate;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public List<Text> getWith() {
        return with;
    }

    @Override
    public void setWith(List<Text> with) {
        if (!Objects.equals(this.with, with)) {
            this.with = with;
            shouldUpdateComponent = true;
        }
    }

    @Override
    public String getRawText() {
        return (translate == null ? "" : Game.getCommon().translate(translate)) + super.getRawText();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractTranslatedText that = (AbstractTranslatedText) o;
        return Objects.equals(translate, that.translate) && Objects.equals(with, that.with);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), translate, with);
    }
}
