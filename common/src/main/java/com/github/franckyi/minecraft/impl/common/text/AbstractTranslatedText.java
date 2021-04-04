package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TranslatedText;

import java.util.List;
import java.util.Objects;

public abstract class AbstractTranslatedText extends AbstractText implements TranslatedText {
    private String translate;
    private List<Text> with;

    protected AbstractTranslatedText(String translate) {
        this.translate = translate;
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
        if (!Objects.equals(this.with , with)) {
            this.with = with;
            shouldUpdateComponent = true;
        }
    }
}
