package com.github.franckyi.guapi.api;

import com.github.franckyi.guapi.api.event.*;

public interface EventTarget {
    default void doTick() {
    }

    default void mouseClicked(MouseButtonEvent event) {
    }

    default void mouseReleased(MouseButtonEvent event) {
    }

    default void mouseDragged(MouseDragEvent event) {
    }

    default void mouseScrolled(MouseScrollEvent event) {
    }

    default void keyPressed(KeyEvent event) {
    }

    default void keyReleased(KeyEvent event) {
    }

    default void charTyped(TypeEvent event) {
    }

    default void mouseMoved(MouseEvent event) {
    }

    default void action(MouseButtonEvent event) {
    }
}
