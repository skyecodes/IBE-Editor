package com.github.franckyi.guapi.api.theme.vanilla;

import com.github.franckyi.guapi.api.event.*;
import com.github.franckyi.guapi.api.node.Labeled;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.minecraft.api.client.render.Matrices;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.Widget;

public interface ForgeVanillaDelegateRenderer extends DelegatedRenderer, IGuiEventListener, IRenderable {
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

    @Override
    default void render(Matrices matrices, int mouseX, int mouseY, float delta) {
        render((MatrixStack) matrices.getMatrixStack(), mouseX, mouseY, delta);
    }

    default void initNode(Node node, Widget widget) {
        widget.active = !node.isDisabled();
        node.xProperty().addListener(newVal -> widget.x = newVal);
        node.yProperty().addListener(newVal -> widget.y = newVal);
        node.widthProperty().addListener(widget::setWidth);
        node.heightProperty().addListener(widget::setHeight);
        node.disabledProperty().addListener(newVal -> widget.active = !newVal);
    }

    default void initLabeled(Labeled node, Widget widget) {
        initNode(node, widget);
        node.labelProperty().addListener(label -> widget.setMessage(label.get()));
    }
}
