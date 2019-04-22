package com.github.franckyi.guapi.event;

@FunctionalInterface
public interface IEventListener<T> {

    void handle(T event);

}
