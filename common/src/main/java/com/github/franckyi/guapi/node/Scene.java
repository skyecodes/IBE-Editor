package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.Renderable;
import com.github.franckyi.guapi.event.*;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.util.Insets;

public class Scene implements ScreenEventHandler, Parent, Renderable {
    private final ObjectProperty<Node> rootProperty = PropertyFactory.ofObject();
    private final BooleanProperty fullScreenProperty = PropertyFactory.ofBoolean();

    private final IntegerProperty widthProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);
    private final IntegerProperty heightProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);

    private final ObjectProperty<Insets> paddingProperty = PropertyFactory.ofObject(Insets.NONE);
    private final BooleanProperty texturedBackgroundProperty = PropertyFactory.ofBoolean();

    protected final ObjectProperty<Node> focusedProperty = PropertyFactory.ofObject();
    private final ObservableObjectValue<Node> focusedPropertyReadOnly = PropertyFactory.readOnly(focusedProperty);
    protected final ObjectProperty<Node> hoveredProperty = PropertyFactory.ofObject();
    private final ObservableObjectValue<Node> hoveredPropertyReadOnly = PropertyFactory.readOnly(hoveredProperty);
    protected final ObjectProperty<MouseButtonEvent> lastClickEvent = PropertyFactory.ofObject();
    private final ObservableObjectValue<Node> activePropertyReadOnly = lastClickEvent.map(MouseEvent::getTarget, null);

    private final ObservableValue<Scene> sceneProperty = ObservableValue.of(this);
    private final ObservableValue<Boolean> disabledProperty = ObservableValue.of(false);

    protected final ScreenEventHandler eventHandlerDelegate = new ScreenEventHandlerDelegate();

    public Scene(Node root) {
        this(root, false);
    }

    public Scene(Node root, boolean fullScreen) {
        this(root, fullScreen, false);
    }

    public Scene(Node root, boolean fullScreen, boolean texturedBackground) {
        rootProperty().addListener((oldVal, newVal) -> {
            if (oldVal != null && oldVal.getParent() == this) {
                oldVal.setParent(null);
                bindFullScreen(oldVal, false);
            }
            if (newVal != null) {
                if (newVal.getParent() != null) {
                    GameHooks.getLogger().error(GUAPI.MARKER, "Can't set Node \"" + newVal +
                            "\" as Scene root: node already has a Parent \"" + newVal.getParent() + "\"");
                    return;
                }
                newVal.setParent(this);
                bindFullScreen(newVal, isFullScreen());
            }
        });
        fullScreenProperty().addListener(newVal -> {
            if (rootProperty().hasValue()) {
                bindFullScreen(getRoot(), newVal);
            }
        });
        setRoot(root);
        setFullScreen(fullScreen);
        setTexturedBackground(texturedBackground);
        paddingProperty().addListener(this::updateChildrenPos);
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

    public boolean isFullScreen() {
        return fullScreenProperty().getValue();
    }

    public BooleanProperty fullScreenProperty() {
        return fullScreenProperty;
    }

    public void setFullScreen(boolean value) {
        fullScreenProperty().setValue(value);
    }

    public int getWidth() {
        return widthProperty().getValue();
    }

    public IntegerProperty widthProperty() {
        return widthProperty;
    }

    public int getHeight() {
        return heightProperty().getValue();
    }

    public IntegerProperty heightProperty() {
        return heightProperty;
    }

    public Insets getPadding() {
        return paddingProperty().getValue();
    }

    public ObjectProperty<Insets> paddingProperty() {
        return paddingProperty;
    }

    public void setPadding(Insets value) {
        paddingProperty().setValue(value);
    }

    public boolean isTexturedBackground() {
        return texturedBackgroundProperty().getValue();
    }

    public BooleanProperty texturedBackgroundProperty() {
        return texturedBackgroundProperty;
    }

    public void setTexturedBackground(boolean value) {
        texturedBackgroundProperty().setValue(value);
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

    public Node getActive() {
        return activeProperty().getValue();
    }

    public ObservableObjectValue<Node> activeProperty() {
        return activePropertyReadOnly;
    }

    public void render(RenderContext<?> ctx) {
        if (rootProperty().hasValue()) {
            getRoot().render(ctx);
        }
    }

    public void tick() {
        if (rootProperty().hasValue()) {
            getRoot().tick();
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
                if (e instanceof MouseButtonEvent) {
                    MouseButtonEvent be = (MouseButtonEvent) e;
                    if (type == ScreenEventType.MOUSE_CLICKED && be.getButton() == MouseButtonEvent.LEFT_BUTTON) {
                        lastClickEvent.setValue(be);
                    } else if (type == ScreenEventType.MOUSE_RELEASED && be.getButton() == MouseButtonEvent.LEFT_BUTTON) {
                        if (lastClickEvent.hasValue()) {
                            if (e.getTarget() == getActive()) {
                                setFocused(e.getTarget());
                                if (e.getTarget() != null) {
                                    this.handleEvent(ScreenEventType.ACTION, new ActionEvent(lastClickEvent.getValue(), (MouseButtonEvent) e));
                                }
                            }
                            lastClickEvent.setValue(null);
                        }
                    }
                } else if (type == ScreenEventType.MOUSE_MOVED) {
                    setHovered(e.getTarget());
                }
            }, () -> {
                if (getFocused() != null && !getFocused().isDisabled()) {
                    getFocused().handleEvent(type, event);
                }
            });
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
    public ObservableValue<Scene> sceneProperty() {
        return sceneProperty;
    }

    @Override
    public ObservableValue<Boolean> disabledProperty() {
        return disabledProperty;
    }

    @Override
    public void updateChildrenPos() {
        if (rootProperty().hasValue()) {
            getRoot().setX(getPadding().getLeft());
            getRoot().setY(getPadding().getTop());
        }
    }

    private void bindFullScreen(Node root, boolean bind) {
        if (bind) {
            root.prefWidthProperty().bind(widthProperty());
            root.prefHeightProperty().bind(heightProperty());
        } else {
            root.prefWidthProperty().unbind();
            root.prefHeightProperty().unbind();
        }
    }

    @Override
    public String toString() {
        return "Scene{" +
                "root=" + getRoot() +
                '}';
    }
}
