package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.guapi.api.event.*;
import com.github.franckyi.guapi.api.node.Labeled;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.fabric.theme.mixin.FabricClickableWidgetMixin;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;

public interface FabricVanillaDelegateRenderer extends DelegatedRenderer, Element, Drawable {
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
    default void render(IMatrices matrices, int mouseX, int mouseY, float delta) {
        render((MatrixStack) matrices, mouseX, mouseY, delta);
    }

    default void initNode(Node node, ClickableWidget widget) {
        widget.active = !node.isDisabled();
        widget.visible = node.isVisible();
        node.xProperty().addListener(newVal -> widget.x = newVal);
        node.yProperty().addListener(newVal -> widget.y = newVal);
        node.widthProperty().addListener(widget::setWidth);
        node.heightProperty().addListener(((FabricClickableWidgetMixin) widget)::setHeight);
        node.disabledProperty().addListener(newVal -> widget.active = !newVal);
        node.visibleProperty().addListener(newVal -> widget.visible = newVal);
    }

    default void initLabeled(Labeled node, ClickableWidget widget) {
        initNode(node, widget);
        node.labelProperty().addListener(label -> widget.setMessage(label.get()));
    }
}
