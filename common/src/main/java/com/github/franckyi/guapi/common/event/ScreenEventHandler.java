package com.github.franckyi.guapi.common.event;

public interface ScreenEventHandler {
    <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event);

    <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener);

    <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener);

}
