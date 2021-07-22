package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.*;
import com.github.franckyi.guapi.api.util.NodeType;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTheme implements Theme {
    private final Map<NodeType<?>, DelegatedSkinProvider<?>> delegatedSkinProviderMap = new HashMap<>();
    private final Map<NodeType<?>, SkinProvider<?>> skinProviderMap = new HashMap<>();

    protected AbstractTheme() {
    }

    protected <N extends Node> void registerSkinProvider(NodeType<? extends N> type, SkinProvider<N> skinProvider) {
        skinProviderMap.put(type, skinProvider);
    }

    protected <N extends Node> void registerSkinInstance(NodeType<? extends N> type, Skin<? super N> instance) {
        registerSkinProvider(type, n -> instance);
    }

    protected <N extends Node> void delegateSkinRenderer(NodeType<? extends N> type, DelegatedSkinProvider<? super N> delegatedSkinProvider) {
        delegatedSkinProviderMap.put(type, delegatedSkinProvider);
    }

    @SuppressWarnings("unchecked")
    protected <N extends Node> DelegatedSkinProvider<? super N> getDelegatedSkinProvider(NodeType<? extends N> type) {
        DelegatedSkinProvider<N> delegatedSkinProvider = (DelegatedSkinProvider<N>) delegatedSkinProviderMap.get(type);
        if (delegatedSkinProvider == null) {
            throw new IllegalStateException("Skin of type " + type + " isn't delegated by Theme " + getClass().getName());
        }
        return delegatedSkinProvider;
    }

    @Override
    public <N extends Node> void registerDelegatedSkinRenderer(NodeType<? extends N> type, DelegatedRendererProvider<? super N> delegatedRendererProvider) {
        DelegatedSkinProvider<? super N> delegatedSkinProvider = getDelegatedSkinProvider(type);
        registerSkinProvider(type, n -> delegatedSkinProvider.provide(delegatedRendererProvider.provide(n)));
        delegatedSkinProviderMap.remove(type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <N extends Node> Skin<? super N> provideSkin(N node, NodeType<? extends N> type) {
        SkinProvider<N> provider = (SkinProvider<N>) skinProviderMap.get(type);
        if (provider == null) {
            throw new IllegalStateException("Skin of type " + node.getClass().getName() + " can't be provided by Theme " + getClass().getName());
        }
        return provider.provide(node);
    }
}
