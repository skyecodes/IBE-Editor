package com.github.franckyi.guapi.common.event;

public interface ScreenEventHandler {
    boolean mouseClicked(MouseButtonEvent event);

    boolean mouseReleased(MouseButtonEvent event);

    boolean mouseDragged(MouseDragEvent event);

    boolean mouseScrolled(MouseScrollEvent event);

    boolean keyPressed(KeyEvent event);

    boolean keyReleased(KeyEvent event);

    boolean charTyped(TypeEvent event);

    void mouseMoved(MouseEvent event);
}
