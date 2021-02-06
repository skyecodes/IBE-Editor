package com.github.franckyi.gamehooks.api.client;

import com.github.franckyi.gamehooks.api.client.renderer.FontHooks;
import com.github.franckyi.gamehooks.api.client.renderer.ShapeHooks;

public interface RendererHooks {
    <M, T> FontHooks<M, T> font();

    <M> ShapeHooks<M> shape();
}
