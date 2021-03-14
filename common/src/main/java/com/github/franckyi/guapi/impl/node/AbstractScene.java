package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.*;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.event.ScreenEventListener;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.api.node.ScreenEventHandler;
import com.github.franckyi.guapi.impl.event.ScreenEventHandlerDelegate;
import com.github.franckyi.guapi.util.Insets;
import com.github.franckyi.guapi.util.ScreenEventType;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.client.render.Matrices;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractScene implements Scene {
    protected final ObjectProperty<Node> focusedProperty = Bindings.getPropertyFactory().ofObject();
    protected final ObjectProperty<Node> hoveredProperty = Bindings.getPropertyFactory().ofObject();
    protected final ScreenEventHandler eventHandlerDelegate = new ScreenEventHandlerDelegate();
    private final ObjectProperty<Node> rootProperty = Bindings.getPropertyFactory().ofObject();
    private final BooleanProperty fullScreenProperty = Bindings.getPropertyFactory().ofBoolean();
    private final IntegerProperty widthProperty = Bindings.getPropertyFactory().ofInteger(Integer.MAX_VALUE);
    private final IntegerProperty heightProperty = Bindings.getPropertyFactory().ofInteger(Integer.MAX_VALUE);
    private final ObjectProperty<Insets> paddingProperty = Bindings.getPropertyFactory().ofObject(Insets.NONE);
    private final BooleanProperty texturedBackgroundProperty = Bindings.getPropertyFactory().ofBoolean();
    private final BooleanProperty closeOnEscProperty = Bindings.getPropertyFactory().ofBoolean(true);
    private final ObservableObjectValue<Node> focusedPropertyReadOnly = Bindings.getPropertyFactory().readOnly(focusedProperty);
    private final ObservableObjectValue<Node> hoveredPropertyReadOnly = Bindings.getPropertyFactory().readOnly(hoveredProperty);
    private final ObservableValue<Scene> sceneProperty = ObservableValue.unmodifiable(this);
    private final ObservableValue<Boolean> disabledProperty = ObservableValue.unmodifiable(false);
    protected boolean shouldUpdateChildrenPos;
    protected final List<Consumer<Scene>> onShowListeners = new ArrayList<>();
    protected final List<Consumer<Scene>> onHideListeners = new ArrayList<>();

    protected AbstractScene() {
        this(null);
    }

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
                    Minecraft.getLogger().error(GUAPI.MARKER, "Can't set Node \"" + newVal +
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
                Minecraft.getClient().getScreenHandler().hideScene();
                e.consume();
            }
        });
        setRoot(root);
        setFullScreen(fullScreen);
        setTexturedBackground(texturedBackground);
        paddingProperty().addListener(this::shouldUpdateChildren);
    }

    @Override
    public ObjectProperty<Node> rootProperty() {
        return rootProperty;
    }

    @Override
    public BooleanProperty fullScreenProperty() {
        return fullScreenProperty;
    }

    @Override
    public IntegerProperty widthProperty() {
        return widthProperty;
    }

    @Override
    public IntegerProperty heightProperty() {
        return heightProperty;
    }

    @Override
    public ObjectProperty<Insets> paddingProperty() {
        return paddingProperty;
    }

    @Override
    public BooleanProperty texturedBackgroundProperty() {
        return texturedBackgroundProperty;
    }

    @Override
    public BooleanProperty closeOnEscProperty() {
        return closeOnEscProperty;
    }

    @Override
    public ObservableObjectValue<Node> focusedProperty() {
        return focusedPropertyReadOnly;
    }

    protected void setFocused(Node value) {
        focusedProperty.setValue(value);
    }

    @Override
    public ObservableObjectValue<Node> hoveredProperty() {
        return hoveredPropertyReadOnly;
    }

    protected void setHovered(Node value) {
        hoveredProperty.setValue(value);
    }

    @Override
    public void render(Matrices matrices, int mouseX, int mouseY, float delta) {
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
        onShowListeners.forEach(listener -> listener.accept(this));
    }

    @Override
    public void onShow(Consumer<Scene> listener) {
        onShowListeners.add(listener);
    }

    @Override
    public void hide() {
        onHideListeners.forEach(listener -> listener.accept(this));
    }

    @Override
    public void onHide(Consumer<Scene> listener) {
        onHideListeners.add(listener);
    }

    @Override
    public <E extends ScreenEvent> void handleEvent(ScreenEventType<E> type, E event) {
        if (getRoot() != null) {
            type.ifMouseEvent(event, (t, e) -> {
                getRoot().handleEvent(type, event);
                if (e instanceof MouseButtonEvent) {
                    MouseButtonEvent be = (MouseButtonEvent) e;
                    if (type == ScreenEventType.MOUSE_CLICKED && be.getButton() == MouseButtonEvent.LEFT_BUTTON) {
                        setFocused(e.getTarget());
                        if (e.getTarget() != null && !e.getTarget().isDisabled()) {
                            e.getTarget().handleEvent(ScreenEventType.ACTION, be);
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

    @Override
    public int getMaxChildrenWidth() {
        return getWidth() - getPadding().getHorizontal();
    }

    @Override
    public int getMaxChildrenHeight() {
        return getHeight() - getPadding().getVertical();
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
