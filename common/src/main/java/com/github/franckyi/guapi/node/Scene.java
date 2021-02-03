package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.event.*;
import com.github.franckyi.guapi.hooks.api.RenderContext;

public class Scene implements ScreenEventHandler {
    private final ObjectProperty<Node> rootProperty = PropertyFactory.ofObject();
    protected final ObjectProperty<Node> focusedProperty = PropertyFactory.ofObject();
    private final ObservableObjectValue<Node> focusedPropertyReadOnly = PropertyFactory.readOnly(focusedProperty);
    protected final ObjectProperty<Node> hoveredProperty = PropertyFactory.ofObject();
    private final ObservableObjectValue<Node> hoveredPropertyReadOnly = PropertyFactory.readOnly(hoveredProperty);

    protected final ScreenEventHandler eventHandlerDelegate = new ScreenEventHandlerDelegate();

    public Scene(Node root) {
        rootProperty().addListener(event -> {
            if (event.getNewValue().getScene() != null) {
                System.err.println("Can't set node \"" + event.getNewValue() + "\" as scene root: node already has a scene");
                return;
            }
            if (event.getOldValue() != null && event.getOldValue().getScene() == this) {
                event.getOldValue().setScene(null);
            }
            if (event.getNewValue() != null) {
                event.getNewValue().setScene(this);
            }
        });
        setRoot(root);
    }

    public Node getRoot() {
        return rootProperty().getValue();
    }

    public ObjectProperty<Node> rootProperty() {
        return rootProperty;
    }

    public void setRoot(Node value) {
        rootProperty().setValue(value);
    }

    public Node getFocused() {
        return focusedProperty().getValue();
    }

    public ObservableObjectValue<Node> focusedProperty() {
        return focusedPropertyReadOnly;
    }

    protected void setFocused(Node value) {
        focusedProperty.setValue(value);
    }

    public Node getHovered() {
        return hoveredProperty().getValue();
    }

    public ObservableObjectValue<Node> hoveredProperty() {
        return hoveredPropertyReadOnly;
    }

    protected void setHovered(Node value) {
        hoveredProperty.setValue(value);
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
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event) {
        if (getRoot() != null) {
            type.ifMouseEvent(event, (t, e) -> {
                getRoot().handleEvent(type, event);
                if (type == ScreenEventType.MOUSE_CLICKED) {
                    setFocused(e.getTarget());
                } else if (type == ScreenEventType.MOUSE_MOVED) {
                    setHovered(e.getTarget());
                }
            });
            if (event instanceof KeyboardEvent && getFocused() != null) {
                getFocused().handleEvent(type, event);
            }
        }
        eventHandlerDelegate.handleEvent(type, event);
    }

    @Override
    public <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener) {
        eventHandlerDelegate.addListener(type, listener);
    }

    @Override
    public <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEvent.Listener<E> listener) {
        eventHandlerDelegate.removeListener(type, listener);
    }

    @Override
    public String toString() {
        return "Scene{" +
                "root=" + getRoot() +
                '}';
    }
}
