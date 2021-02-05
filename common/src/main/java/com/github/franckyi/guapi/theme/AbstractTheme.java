package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTheme implements Theme {
    private final Map<Class<?>, Skin<?>> map = new HashMap<>();

    protected AbstractTheme() {
    }

    protected <T extends Node> void register(Class<T> tClass, Skin<T> skin) {
        map.put(tClass, skin);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> Skin<T> getSkin(T node) {
        return (Skin<T>) map.get(node.getClass());
    }
}
