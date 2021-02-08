package com.github.franckyi.guapi.hooks.impl;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.event.*;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.hooks.api.ScreenHandler;
import com.github.franckyi.guapi.node.Scene;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public abstract class AbstractScreenHandler<T> implements ScreenHandler {
    private final Queue<Scene> scenes = new LinkedList<>();
    private final ObjectProperty<Scene> currentSceneProperty = PropertyFactory.ofObject();
    private final IntegerProperty widthProperty = PropertyFactory.ofInteger();
    private final IntegerProperty heightProperty = PropertyFactory.ofInteger();
    private T screen;

    public AbstractScreenHandler() {
        currentSceneProperty().addListener((oldVal, newVal) -> {
            if (newVal == null) {
                close();
            } else {
                newVal.widthProperty().bind(widthProperty);
                newVal.heightProperty().bind(heightProperty);
                newVal.show();
                if (oldVal == null) {
                    open();
                } else {
                    oldVal.widthProperty().unbind();
                    oldVal.heightProperty().unbind();
                    oldVal.hide();
                }
            }
        });
    }

    protected void initScreen(T screen) {
        this.screen = screen;
    }

    @Override
    public void show(Scene scene) {
        scenes.add(scene);
        setCurrentScene(scene);
    }

    @Override
    public void hide() {
        scenes.poll();
        setCurrentScene(scenes.peek());
    }

    public Scene getCurrentScene() {
        return currentSceneProperty().getValue();
    }

    public ObjectProperty<Scene> currentSceneProperty() {
        return currentSceneProperty;
    }

    public void setCurrentScene(Scene value) {
        currentSceneProperty().setValue(value);
    }

    protected void open() {
        getMinecraftScreenHandler().accept(screen);
    }

    protected void close() {
        getMinecraftScreenHandler().accept(null);
    }

    protected abstract Consumer<T> getMinecraftScreenHandler();

    public void render(RenderContext<?> ctx) {
        try {
            getCurrentScene().render(ctx);
        } catch (Exception e) {
            GameHooks.logger().error(GUAPI.MARKER, "Error while rendering GUAPI Scene", e);
            hide();
        }
    }

    public void tick() {
        if (currentSceneProperty().hasValue()) {
            try {
                getCurrentScene().tick();
            } catch (Exception e) {
                GameHooks.logger().error(GUAPI.MARKER, "Error while ticking GUAPI Scene", e);
                hide();
            }
        }
    }

    public void updateSize(int width, int height) {
        widthProperty.setValue(width);
        heightProperty.setValue(height);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return handleEvent(ScreenEventType.MOUSE_CLICKED, new MouseButtonEvent(mouseX, mouseY, button));
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return handleEvent(ScreenEventType.MOUSE_RELEASED, new MouseButtonEvent(mouseX, mouseY, button));
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return handleEvent(ScreenEventType.MOUSE_DRAGGED, new MouseDragEvent(mouseX, mouseY, button, deltaX, deltaY));
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return handleEvent(ScreenEventType.MOUSE_SCOLLED, new MouseScrollEvent(mouseX, mouseY, amount));
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return handleEvent(ScreenEventType.KEY_PRESSED, new KeyEvent(keyCode, scanCode, modifiers));
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return handleEvent(ScreenEventType.KEY_RELEASED, new KeyEvent(keyCode, scanCode, modifiers));
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return handleEvent(ScreenEventType.CHAR_TYPED, new TypeEvent(chr, modifiers));
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        handleEvent(ScreenEventType.MOUSE_MOVED, new MouseEvent(mouseX, mouseY));
    }

    protected <E extends ScreenEvent> boolean handleEvent(ScreenEventType<E> type, E event) {
        if (!currentSceneProperty().hasValue()) return false;
        try {
            getCurrentScene().handleEvent(type, event);
        } catch (Exception e) {
            GameHooks.logger().error(GUAPI.MARKER, "Error while handling " + type.getName() + " event on GUAPI Scene", e);
            hide();
        }
        return event.isConsumed();
    }
}
