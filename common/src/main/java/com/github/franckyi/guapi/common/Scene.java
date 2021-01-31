package com.github.franckyi.guapi.common;

import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.data.Property;
import com.github.franckyi.guapi.common.event.*;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.node.Node;

public class Scene implements ScreenEventHandler {
    private final Property<Node> rootProperty = new ObjectProperty<>();

    public Scene(Node root) {
        setRoot(root);
    }

    public Node getRoot() {
        return rootProperty.getValue();
    }

    public Property<Node> rootProperty() {
        return rootProperty;
    }

    public void setRoot(Node value) {
        rootProperty.setValue(value);
    }

    public void render(RenderContext<?> ctx) {
        if (getRoot() != null) {
            getRoot().render(ctx);
        }
    }

    public void show() {

    }

    public void hide() {

    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event) {
        return rootProperty().mapIfPresent(node -> node.mouseClicked(event), false);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        return rootProperty().mapIfPresent(node -> node.mouseReleased(event), false);
    }

    @Override
    public boolean mouseDragged(MouseDragEvent event) {
        return rootProperty().mapIfPresent(node -> node.mouseDragged(event), false);
    }

    @Override
    public boolean mouseScrolled(MouseScrollEvent event) {
        return rootProperty().mapIfPresent(node -> node.mouseScrolled(event), false);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        return rootProperty().mapIfPresent(node -> node.keyPressed(event), false);
    }

    @Override
    public boolean keyReleased(KeyEvent event) {
        return rootProperty().mapIfPresent(node -> node.keyReleased(event), false);
    }

    @Override
    public boolean charTyped(TypeEvent event) {
        return rootProperty().mapIfPresent(node -> node.charTyped(event), false);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        rootProperty().doIfPresent(node -> node.mouseMoved(event));
    }
}
