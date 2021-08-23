package com.github.franckyi.gameadapter.api.common.text;

import com.github.franckyi.gameadapter.Game;

public interface ITextEvent {
    static ITextEvent click(String action, String value) {
        return Game.getCommon().createTextClickEvent(action, value);
    }

    String getAction();

    String getValue();
}
