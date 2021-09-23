package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.api.theme.SkinSupplier;
import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.api.util.NodeType;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTheme implements Theme {
    private final Map<NodeType<?>, SkinSupplier<?>> skinSupplierMap = new HashMap<>();

    protected AbstractTheme() {
    }

    protected <N extends Node> void registerSkinSupplier(NodeType<? extends N> type, SkinSupplier<N> skinSupplier) {
        skinSupplierMap.put(type, skinSupplier);
    }

    protected <N extends Node> void registerSkinInstance(NodeType<? extends N> type, Skin<? super N> instance) {
        registerSkinSupplier(type, n -> instance);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <N extends Node> Skin<? super N> supplySkin(N node, NodeType<? extends N> type) {
        SkinSupplier<N> provider = (SkinSupplier<N>) skinSupplierMap.get(type);
        if (provider == null) {
            throw new IllegalStateException("Skin of type " + node.getClass().getName() + " can't be provided by Theme " + getClass().getName());
        }
        return provider.provide(node);
    }
}
