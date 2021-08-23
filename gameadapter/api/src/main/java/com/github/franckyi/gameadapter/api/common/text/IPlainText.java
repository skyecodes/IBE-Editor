package com.github.franckyi.gameadapter.api.common.text;

import com.github.franckyi.gameadapter.Game;

public interface IPlainText extends IText {
    static IPlainText create(String text) {
        return Game.getCommon().createPlainText(text);
    }

    String getText();
}
