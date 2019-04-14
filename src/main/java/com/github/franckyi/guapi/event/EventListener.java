package com.github.franckyi.guapi.event;

@FunctionalInterface
public interface EventListener<T> {

    void handle(T event);

}
