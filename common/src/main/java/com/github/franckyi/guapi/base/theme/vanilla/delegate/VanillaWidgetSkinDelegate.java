package com.github.franckyi.guapi.base.theme.vanilla.delegate;

import com.github.franckyi.guapi.api.EventTarget;
import com.github.franckyi.guapi.api.Renderable;
import com.github.franckyi.guapi.api.event.*;
import com.github.franckyi.guapi.api.node.Labeled;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.mixin.AbstractWidgetMixin;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;

public interface VanillaWidgetSkinDelegate extends Renderable, EventTarget, GuiEventListener {
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
        mouseScrolled(event.getMouseX(), event.getMouseY(), event.getDeltaX(), event.getDeltaY());
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

    default void initNodeWidget(Node node) {
        AbstractWidget widget = (AbstractWidget) this;
        widget.active = !node.isDisabled();
        widget.visible = node.isVisible();
        node.xProperty().addListener(widget::setX);
        node.yProperty().addListener(widget::setY);
        node.widthProperty().addListener(widget::setWidth);
        node.heightProperty().addListener(((AbstractWidgetMixin) widget)::setHeight);
        node.disabledProperty().addListener(newVal -> widget.active = !newVal);
        node.visibleProperty().addListener(newVal -> widget.visible = newVal);
    }

    default void initLabeledWidget(Labeled node) {
        initNodeWidget(node);
        node.labelProperty().addListener(((AbstractWidget) this)::setMessage);
    }
}
