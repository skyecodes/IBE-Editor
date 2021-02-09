package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.EventTarget;
import com.github.franckyi.guapi.event.ScreenEvent;
import com.github.franckyi.guapi.event.ScreenEventType;
import com.github.franckyi.guapi.node.Node;
import com.github.franckyi.guapi.util.HookUtil;

public interface Skin<N extends Node> extends EventTarget, HookUtil {
    default boolean preRender(N node) {
        return false;
    }

    void render(N node, Object matrices, int mouseX, int mouseY, float delta);

    int computeWidth(N node);

    int computeHeight(N node);

    <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event);
}
