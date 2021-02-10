package com.github.franckyi.guapi.impl.event;

import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventHandler;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.util.ScreenEventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenEventHandlerDelegate implements ScreenEventHandler {
    private final Map<ScreenEventType<?>, List<ScreenEventListener<?>>> eventListenerMap = new HashMap<>();

    public ScreenEventHandlerDelegate() {
        ScreenEventType.VALUES.forEach(type -> eventListenerMap.put(type, new ArrayList<>()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event) {
        for (ScreenEventListener<?> listener : eventListenerMap.get(type)) {
            ((ScreenEventListener<E>) listener).handle(event);
        }
    }

    @Override
    public <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEventListener<E> listener) {
        eventListenerMap.get(type).add(listener);
    }

    @Override
    public <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEventListener<E> listener) {
        eventListenerMap.get(type).remove(listener);
    }
}
