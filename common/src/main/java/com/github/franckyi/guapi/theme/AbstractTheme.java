package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractTheme implements Theme {
    private final Map<Class<?>, SkinProvider<?>> map = new HashMap<>();

    protected AbstractTheme() {
    }

    protected <N extends Node> void registerSkinProvider(Class<N> nodeClass, SkinProvider<N> skin) {
        map.put(nodeClass, skin);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <N extends Node> Skin<N> provideSkin(N node) {
        return ((SkinProvider<N>) map.get(node.getClass())).provide(node);
    }
}
