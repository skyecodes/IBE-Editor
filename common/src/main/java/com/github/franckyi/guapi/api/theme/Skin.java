package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.api.EventTarget;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.ScreenEventType;

public interface Skin<N extends Node> extends EventTarget {
    default <M> boolean preRender(N node, M matrices, int mouseX, int mouseY, float delta) {
        return false;
    }

    <M> void render(N node, M matrices, int mouseX, int mouseY, float delta);

    default <M> void postRender(N node, M matrices, int mouseX, int mouseY, float delta) {
        if (node.tooltipProperty().hasValue() && node.isHovered() && !node.isDisabled()) {
            GameHooks.client().getRenderer().drawTooltip(matrices, node.getTooltip(), mouseX, mouseY);
        }
    }

    int computeWidth(N node);

    int computeHeight(N node);

    <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event);
}
