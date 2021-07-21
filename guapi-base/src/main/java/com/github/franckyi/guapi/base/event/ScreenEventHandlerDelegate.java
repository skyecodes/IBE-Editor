package com.github.franckyi.guapi.base.event;

import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.api.node.ScreenEventHandler;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ScreenEventHandlerDelegate implements ScreenEventHandler {
    private final Multimap<ScreenEventType<?>, ScreenEventListener<?>> eventListenerMap = HashMultimap.create();

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
