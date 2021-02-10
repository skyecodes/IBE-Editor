package com.github.franckyi.guapi.api.event;

@FunctionalInterface
public interface ScreenEventListener<E extends ScreenEvent> {
    void handle(E event);
}
