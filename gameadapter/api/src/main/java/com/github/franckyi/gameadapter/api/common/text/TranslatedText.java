package com.github.franckyi.gameadapter.api.common.text;

import java.util.List;

public interface TranslatedText extends Text {
    String getTranslate();

    void setTranslate(String translate);

    List<Text> getWith();

    void setWith(List<Text> with);
}
