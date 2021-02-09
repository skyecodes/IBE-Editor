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

    protected <N extends Node> void registerSkinInstance(Class<N> nodeClass, Skin<? super N> instance) {
        registerSkinProvider(nodeClass, n -> instance);
    }

    protected <N extends Node> void delegateSkinProvider(Class<N> nodeClass, DelegatedSkinProvider<? super N> delegatedSkinProvider) {
        delegatedSkinProviderMap.put(nodeClass, delegatedSkinProvider);
    }

    public <N extends Node> void registerDelegatedSkinProvider(Class<N> nodeClass, DelegatedRendererProvider<? super N> delegatedRendererProvider) {
        DelegatedSkinProvider<? super N> delegatedSkinProvider = getDelegatedSkinProvider(nodeClass);
        registerSkinProvider(nodeClass, n -> delegatedSkinProvider.provide(delegatedRendererProvider.provide(n)));
        delegatedSkinProviderMap.remove(nodeClass);
    }

    @SuppressWarnings("unchecked")
    protected <N extends Node> DelegatedSkinProvider<? super N> getDelegatedSkinProvider(Class<N> nodeClass) {
        DelegatedSkinProvider<N> delegatedSkinProvider = (DelegatedSkinProvider<N>) delegatedSkinProviderMap.get(nodeClass);
        if (delegatedSkinProvider == null) {
            throw new IllegalStateException("Skin of type " + nodeClass.getName() + " isn't delegated by Theme " + getClass().getName());
        }
        return delegatedSkinProvider;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <N extends Node> Skin<? super N> provideSkin(N node) {
        SkinProvider<N> provider = (SkinProvider<N>) skinProviderMap.get(node.getClass());
        if (provider == null) {
            throw new IllegalStateException("Skin of type " + node.getClass().getName() + " can't be provided by Theme " + getClass().getName());
        }
        return provider.provide(node);
    }
}
