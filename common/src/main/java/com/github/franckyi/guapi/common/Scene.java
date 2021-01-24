package com.github.franckyi.guapi.common;

import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.data.Property;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.node.Node;

public class Scene {
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

    public void onShow() {

    }

    public void onHide() {

    }
}
