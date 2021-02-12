package com.github.franckyi.guapi.api.theme.vanilla;

import com.github.franckyi.guapi.api.event.*;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;

public interface FabricVanillaDelegateRenderer extends DelegatedRenderer<MatrixStack>, Element {
    @Override
    default void mouseClicked(MouseButtonEvent event) {
        mouseClicked(event.getMouseX(), event.getMouseY(), event.getButton());
    }

    @Override
    default void mouseReleased(MouseButtonEvent event) {
        mouseReleased(event.getMouseX(), event.getMouseY(), event.getButton());
    }

    @Override
    default void mouseDragged(MouseDragEvent event) {
        mouseDragged(event.getMouseX(), event.getMouseY(), event.getButton(), event.getDeltaX(), event.getDeltaY());
    }

    @Override
    default void mouseScrolled(MouseScrollEvent event) {
        mouseScrolled(event.getMouseX(), event.getMouseY(), event.getAmount());
    }

    @Override
    default void keyPressed(KeyEvent event) {
        keyPressed(event.getKeyCode(), event.getScanCode(), event.getModifiers());
    }

    @Override
    default void keyReleased(KeyEvent event) {
        keyReleased(event.getKeyCode(), event.getScanCode(), event.getModifiers());
    }

    @Override
    default void charTyped(TypeEvent event) {
        charTyped(event.getCharacter(), event.getModifiers());
    }

    @Override
    default void mouseMoved(MouseEvent event) {
        mouseMoved(event.getMouseX(), event.getMouseY());
    }
}
