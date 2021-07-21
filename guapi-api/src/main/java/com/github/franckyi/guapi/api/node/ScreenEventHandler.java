package com.github.franckyi.guapi.api.node;

import com.github.franckyi.guapi.api.event.*;
import com.github.franckyi.guapi.api.util.ScreenEventType;

public interface ScreenEventHandler {
    <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event);

    <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEventListener<E> listener);

    <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEventListener<E> listener);

    default <E extends ScreenEvent> ScreenEventListener<E> addListener(ScreenEventType<E> type, Runnable listener) {
        ScreenEventListener<E> realListener = e -> listener.run();
        addListener(type, realListener);
        return realListener;
    }

    default void onMouseClick(ScreenEventListener<MouseButtonEvent> listener) {
        addListener(ScreenEventType.MOUSE_CLICKED, listener);
    }

    default void onMouseRelease(ScreenEventListener<MouseButtonEvent> listener) {
        addListener(ScreenEventType.MOUSE_RELEASED, listener);
    }

    default void onMouseDrag(ScreenEventListener<MouseDragEvent> listener) {
        addListener(ScreenEventType.MOUSE_DRAGGED, listener);
    }

    default void onMouseScroll(ScreenEventListener<MouseScrollEvent> listener) {
        addListener(ScreenEventType.MOUSE_SCOLLED, listener);
    }

    default void onKeyPress(ScreenEventListener<KeyEvent> listener) {
        addListener(ScreenEventType.KEY_PRESSED, listener);
    }

    default void onKeyRelease(ScreenEventListener<KeyEvent> listener) {
        addListener(ScreenEventType.KEY_RELEASED, listener);
    }

    default void onCharType(ScreenEventListener<TypeEvent> listener) {
        addListener(ScreenEventType.CHAR_TYPED, listener);
    }

    default void onMouseMove(ScreenEventListener<MouseEvent> listener) {
        addListener(ScreenEventType.MOUSE_MOVED, listener);
    }

    default void onAction(ScreenEventListener<MouseButtonEvent> listener) {
        addListener(ScreenEventType.ACTION, listener);
    }

    default void onMouseClick(Runnable listener) {
        addListener(ScreenEventType.MOUSE_CLICKED, listener);
    }

    default void onMouseRelease(Runnable listener) {
        addListener(ScreenEventType.MOUSE_RELEASED, listener);
    }

    default void onMouseDrag(Runnable listener) {
        addListener(ScreenEventType.MOUSE_DRAGGED, listener);
    }

    default void onMouseScroll(Runnable listener) {
        addListener(ScreenEventType.MOUSE_SCOLLED, listener);
    }

    default void onKeyPress(Runnable listener) {
        addListener(ScreenEventType.KEY_PRESSED, listener);
    }

    default void onKeyRelease(Runnable listener) {
        addListener(ScreenEventType.KEY_RELEASED, listener);
    }

    default void onCharType(Runnable listener) {
        addListener(ScreenEventType.CHAR_TYPED, listener);
    }

    default void onMouseMove(Runnable listener) {
        addListener(ScreenEventType.MOUSE_MOVED, listener);
    }

    default void onAction(Runnable listener) {
        addListener(ScreenEventType.ACTION, listener);
    }
}
