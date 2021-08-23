package com.github.franckyi.gameadapter.api.common.text;

import com.github.franckyi.gameadapter.Game;

import java.util.List;

public interface ITranslatedText extends IText {
    static ITranslatedText create(String key) {
        return Game.getCommon().createTranslatedText(key);
    }

    static ITranslatedText create(String key, Object... args) {
        return Game.getCommon().createTranslatedText(key, args);
    }

    String getTranslate();

    @Deprecated
    void setTranslate(String translate);

    List<IText> getWith();

    @Deprecated
    void setWith(List<IText> with);
}
