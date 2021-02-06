package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

public interface Theme {
    <T extends Node> Skin<T> provideSkin(T node);
}
