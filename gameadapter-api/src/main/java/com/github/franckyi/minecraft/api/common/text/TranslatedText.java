package com.github.franckyi.minecraft.api.common.text;

import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.List;

public interface TranslatedText extends Text {
    String getTranslate();

    void setTranslate(String translate);

    List<Text> getWith();

    void setWith(List<Text> with);
}
