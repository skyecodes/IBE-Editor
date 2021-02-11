package com.github.franckyi.guapi.api.node;

import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.util.ScreenEventType;

public interface ScreenEventHandler {
    <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event);

    <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEventListener<E> listener);

    <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEventListener<E> listener);
}
