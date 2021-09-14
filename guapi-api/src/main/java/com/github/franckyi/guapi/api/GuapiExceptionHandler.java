package com.github.franckyi.guapi.api;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.api.util.ScreenEventType;

public interface GuapiExceptionHandler {
    GuapiExceptionHandler NONE = new GuapiExceptionHandler() {
        @Override
        public <E extends ScreenEvent> void handleEventException(Exception e, ScreenEventType<E> type, E event, Scene currentScene) {
        }

        @Override
        public void handleTickException(Exception e, Scene currentScene) {
        }

        @Override
        public void handleRenderException(Exception e, IMatrices matrices, int mouseX, int mouseY, float delta, Scene currentScene) {
        }
    };

    <E extends ScreenEvent> void handleEventException(Exception e, ScreenEventType<E> type, E event, Scene currentScene);

    void handleTickException(Exception e, Scene currentScene);

    void handleRenderException(Exception e, IMatrices matrices, int mouseX, int mouseY, float delta, Scene currentScene);
}
