package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.data.SimpleObjectProperty;
import com.github.franckyi.guapi.common.data.ObservableValue;
import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.data.ReadOnlyObjectProperty;
import com.github.franckyi.guapi.common.event.*;
import com.github.franckyi.guapi.common.hooks.RenderContext;

public class Scene implements ScreenEventHandler {
    protected final ObjectProperty<Node> focusedProperty = new SimpleObjectProperty<>();
    protected final ObjectProperty<Node> hoveredProperty = new SimpleObjectProperty<>();
    protected final ScreenEventHandler eventHandlerDelegate = new ScreenEventHandlerDelegate();
    private final ObjectProperty<Node> rootProperty = new SimpleObjectProperty<>();
    private final ObservableValue<Node> focusedPropertyReadOnly = new ReadOnlyObjectProperty<>(focusedProperty);
    private final ObservableValue<Node> hoveredPropertyReadOnly = new ReadOnlyObjectProperty<>(hoveredProperty);

    public Scene(Node root) {
        focusedProperty().addListener(event -> System.out.println("focused=" + event.getNewValue()));
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

    public void setRoot(Node value) {
        rootProperty().setValue(value);
    }

    public ObjectProperty<Node> rootProperty() {
        return rootProperty;
    }

    public Node getFocused() {
        return focusedProperty().getValue();
    }

    protected void setFocused(Node value) {
        focusedProperty.setValue(value);
    }

    public ObservableValue<Node> focusedProperty() {
        return focusedPropertyReadOnly;
    }

    public Node getHovered() {
        return hoveredProperty().getValue();
    }

    protected void setHovered(Node value) {
        hoveredProperty.setValue(value);
    }

    public ObservableValue<Node> hoveredProperty() {
        return hoveredPropertyReadOnly;
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
