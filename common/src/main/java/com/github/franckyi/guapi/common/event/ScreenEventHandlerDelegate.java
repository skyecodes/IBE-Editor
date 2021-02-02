package com.github.franckyi.guapi.common.event;

import java.util.*;

public class ScreenEventHandlerDelegate implements ScreenEventHandler {
    private final Map<ScreenEventType<?>, List<ScreenEvent.Listener<?>>> eventListenerMap = new HashMap<>();

    public ScreenEventHandlerDelegate() {
        ScreenEventType.VALUES.forEach(type -> eventListenerMap.put(type, new ArrayList<>()));
    }

    @Override
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event) {
        for (ScreenEvent.Listener<?> listener : eventListenerMap.get(type)) {
            ((ScreenEvent.Listener<E>) listener).handle(event);
        }
    }

    @Override
    public <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener) {
        eventListenerMap.get(type).add(listener);
    }

    @Override
    public <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener) {
        eventListenerMap.get(type).remove(listener);
    }
}
