package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.Game;

public interface IIdentifier {
    static IIdentifier of(String namespace, String path) {
        return Game.getCommon().createIdentifier(namespace, path);
    }

    static IIdentifier of(String id) {
        return Game.getCommon().createIdentifier(id);
    }

    static IIdentifier parse(String id) {
        return Game.getCommon().parseIdentifier(id);
    }

    String toString();
}
