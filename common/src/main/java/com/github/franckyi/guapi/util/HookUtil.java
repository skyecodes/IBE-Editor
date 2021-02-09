package com.github.franckyi.guapi.util;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import com.github.franckyi.gamehooks.api.common.text.TextFactory;

public interface HookUtil {
    default <M, T> FontRenderer<M, T> font() {
        return GameHooks.client().fontRenderer();
    }

    default <M> ShapeRenderer<M> shape() {
        return GameHooks.client().shapeRenderer();
    }

    default <T> TextFactory<T> text() {
        return GameHooks.common().text();
    }
}
