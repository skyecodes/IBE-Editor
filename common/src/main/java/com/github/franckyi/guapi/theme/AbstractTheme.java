package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTheme implements Theme {
    private final Map<Class<?>, DelegatedSkinProvider<?>> delegatedSkinProviderMap = new HashMap<>();
    private final Map<Class<?>, SkinProvider<?>> skinProviderMap = new HashMap<>();

    protected AbstractTheme() {
    }

    protected <N extends Node> void registerSkinProvider(Class<N> nodeClass, SkinProvider<N> skinProvider) {
        skinProviderMap.put(nodeClass, skinProvider);
    }

    protected <N extends Node> void registerSkinInstance(Class<N> nodeClass, Skin<N> instance) {
        registerSkinProvider(nodeClass, n -> instance);
    }

    protected <N extends Node> void delegateSkinProvider(Class<N> nodeClass, DelegatedSkinProvider<N> delegatedSkinProvider) {
        delegatedSkinProviderMap.put(nodeClass, delegatedSkinProvider);
    }

    public <N extends Node> void registerDelegatedSkinProvider(Class<N> nodeClass, DelegatedRendererProvider<N> delegatedRendererProvider) {
        registerSkinProvider(nodeClass, n -> getDelegatedSkinProvider(nodeClass).provide(delegatedRendererProvider.provide(n)));
    }

    @SuppressWarnings("unchecked")
    protected <N extends Node> DelegatedSkinProvider<N> getDelegatedSkinProvider(Class<N> nodeClass) {
        return (DelegatedSkinProvider<N>) delegatedSkinProviderMap.get(nodeClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <N extends Node> Skin<N> provideSkin(N node) {
        return ((SkinProvider<N>) skinProviderMap.get(node.getClass())).provide(node);
    }
}
