package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.*;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventHandler;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.impl.event.MouseButtonEventImpl;
import com.github.franckyi.guapi.impl.event.ScreenEventHandlerDelegate;
import com.github.franckyi.guapi.util.Insets;
import com.github.franckyi.guapi.util.ScreenEventType;
import org.lwjgl.glfw.GLFW;

public abstract class AbstractScene implements Scene {
    protected final ObjectProperty<Node> focusedProperty = PropertyFactory.ofObject();
    protected final ObjectProperty<Node> hoveredProperty = PropertyFactory.ofObject();
    protected final ScreenEventHandler eventHandlerDelegate = new ScreenEventHandlerDelegate();
    private final ObjectProperty<Node> rootProperty = PropertyFactory.ofObject();
    private final BooleanProperty fullScreenProperty = PropertyFactory.ofBoolean();
    private final IntegerProperty widthProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);
    private final IntegerProperty heightProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);
    private final ObjectProperty<Insets> paddingProperty = PropertyFactory.ofObject(Insets.NONE);
    private final BooleanProperty texturedBackgroundProperty = PropertyFactory.ofBoolean();
    private final BooleanProperty closeOnEscProperty = PropertyFactory.ofBoolean(true);
    private final ObservableObjectValue<Node> focusedPropertyReadOnly = PropertyFactory.readOnly(focusedProperty);
    private final ObservableObjectValue<Node> hoveredPropertyReadOnly = PropertyFactory.readOnly(hoveredProperty);
    private final ObservableValue<Scene> sceneProperty = ObservableValue.of(this);
    private final ObservableValue<Boolean> disabledProperty = ObservableValue.of(false);
    protected boolean shouldUpdateChildrenPos;

    protected AbstractScene(Node root) {
        this(root, false);
    }

    protected AbstractScene(Node root, boolean fullScreen) {
        this(root, fullScreen, false);
    }

    protected AbstractScene(Node root, boolean fullScreen, boolean texturedBackground) {
        rootProperty().addListener((oldVal, newVal) -> {
            if (oldVal != null && oldVal.getParent() == this) {
                oldVal.setParent(null);
                bindFullScreen(oldVal, false);
            }
            if (newVal != null) {
                if (newVal.getParent() != null) {
                    GameHooks.logger().error(GUAPI.MARKER, "Can't set Node \"" + newVal +
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
        addListener(ScreenEventType.KEY_PRESSED, e -> {
            if (e.getKeyCode() == GLFW.GLFW_KEY_ESCAPE && isCloseOnEsc()) {
                GUAPI.getScreenHandler().hide();
                e.consume();
            }
        });
        setRoot(root);
        setFullScreen(fullScreen);
        setTexturedBackground(texturedBackground);
        paddingProperty().addListener(this::shouldUpdateChildren);
    }

    @Override
    public Node getRoot() {
        return rootProperty().getValue();
    }

    @Override
    public ObjectProperty<Node> rootProperty() {
        return rootProperty;
    }

    @Override
    public void setRoot(Node value) {
        rootProperty().setValue(value);
    }

    @Override
    public boolean isFullScreen() {
        return fullScreenProperty().getValue();
    }

    @Override
    public BooleanProperty fullScreenProperty() {
        return fullScreenProperty;
    }

    @Override
    public void setFullScreen(boolean value) {
        fullScreenProperty().setValue(value);
    }

    public int getWidth() {
        return widthProperty().getValue();
    }

    @Override
    public IntegerProperty widthProperty() {
        return widthProperty;
    }

    public int getHeight() {
        return heightProperty().getValue();
    }

    @Override
    public IntegerProperty heightProperty() {
        return heightProperty;
    }

    public Insets getPadding() {
        return paddingProperty().getValue();
    }

    @Override
    public ObjectProperty<Insets> paddingProperty() {
        return paddingProperty;
    }

    @Override
    public void setPadding(Insets value) {
        paddingProperty().setValue(value);
    }

    @Override
    public boolean isTexturedBackground() {
        return texturedBackgroundProperty().getValue();
    }

    @Override
    public BooleanProperty texturedBackgroundProperty() {
        return texturedBackgroundProperty;
    }

    @Override
    public void setTexturedBackground(boolean value) {
        texturedBackgroundProperty().setValue(value);
    }

    @Override
    public boolean isCloseOnEsc() {
        return closeOnEscProperty().getValue();
    }

    @Override
    public BooleanProperty closeOnEscProperty() {
        return closeOnEscProperty;
    }

    @Override
    public void setCloseOnEsc(boolean value) {
        closeOnEscProperty().setValue(value);
    }

    @Override
    public Node getFocused() {
        return focusedProperty().getValue();
    }

    @Override
    public ObservableObjectValue<Node> focusedProperty() {
        return focusedPropertyReadOnly;
    }

    protected void setFocused(Node value) {
        focusedProperty.setValue(value);
    }

    @Override
    public Node getHovered() {
        return hoveredProperty().getValue();
    }

    @Override
    public ObservableObjectValue<Node> hoveredProperty() {
        return hoveredPropertyReadOnly;
    }

    protected void setHovered(Node value) {
        hoveredProperty.setValue(value);
    }

    @Override
    public void render(Object matrices, int mouseX, int mouseY, float delta) {
        if (shouldUpdateChildrenPos) {
            updateChildrenPos();
            shouldUpdateChildrenPos = false;
        }
        if (rootProperty().hasValue()) {
            while (getRoot().preRender(matrices, mouseX, mouseY, delta)) ;
            getRoot().render(matrices, mouseX, mouseY, delta);
            getRoot().postRender(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void shouldComputeSize() {
    }

    @Override
    public void shouldUpdateChildren() {
        shouldUpdateChildrenPos = true;
    }

    @Override
    public void tick() {
        if (rootProperty().hasValue()) {
            getRoot().tick();
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event) {
        if (getRoot() != null) {
            type.ifMouseEvent(event, (t, e) -> {
                getRoot().handleEvent(type, event);
                if (e instanceof MouseButtonEventImpl) {
                    MouseButtonEventImpl be = (MouseButtonEventImpl) e;
                    if (type == ScreenEventType.MOUSE_CLICKED && be.getButton() == MouseButtonEventImpl.LEFT_BUTTON) {
                        setFocused(e.getTarget());
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
    public <E extends ScreenEvent> void addListener(ScreenEventType<E> type, ScreenEventListener<E> listener) {
        eventHandlerDelegate.addListener(type, listener);
    }

    @Override
    public <E extends ScreenEvent> void removeListener(ScreenEventType<E> type, ScreenEventListener<E> listener) {
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

    protected void updateChildrenPos() {
        if (rootProperty().hasValue()) {
            getRoot().setX(getPadding().getLeft());
            getRoot().setY(getPadding().getTop());
        }
    }

    protected void bindFullScreen(Node root, boolean bind) {
        if (bind) {
            root.prefWidthProperty().bind(widthProperty());
            root.prefHeightProperty().bind(heightProperty());
        } else {
            root.prefWidthProperty().unbind();
            root.prefHeightProperty().unbind();
        }
    }
}
