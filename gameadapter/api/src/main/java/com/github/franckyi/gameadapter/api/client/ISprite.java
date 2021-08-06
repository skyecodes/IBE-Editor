package com.github.franckyi.gameadapter.api.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.IIdentifier;

public interface ISprite {
    static ISprite fromEffect(IIdentifier id) {
        return Game.getClient().getEffectSprite(id);
    }
}
