package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.EventTarget;
import com.github.franckyi.guapi.event.ScreenEvent;
import com.github.franckyi.guapi.event.ScreenEventType;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Node;

public interface Skin<N extends Node> extends EventTarget {
    void render(N node, RenderContext<?> ctx);

    int computeWidth(N node);

    int computeHeight(N node);

    <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event);
}
