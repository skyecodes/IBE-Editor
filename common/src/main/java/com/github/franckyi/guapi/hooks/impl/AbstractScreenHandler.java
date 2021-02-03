package com.github.franckyi.guapi.hooks.impl;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.event.*;
import com.github.franckyi.guapi.hooks.api.ScreenHandler;
import com.github.franckyi.guapi.node.Scene;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public abstract class AbstractScreenHandler<T> implements ScreenHandler {
    private final Queue<Scene> scenes = new LinkedList<>();
    private final ObjectProperty<Scene> currentSceneProperty = PropertyFactory.ofObject();
    private T screen;

    public AbstractScreenHandler() {
        currentSceneProperty.addListener(event -> {
            if (event.getNewValue() == null) {
                close();
            } else {
                event.getNewValue().show();
                if (event.getOldValue() == null) {
                    open();
                } else {
                    event.getOldValue().hide();
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

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (getCurrentScene() == null) return false;
        MouseButtonEvent event = new MouseButtonEvent(mouseX, mouseY, button);
        getCurrentScene().handleEvent(ScreenEventType.MOUSE_CLICKED, event);
        return event.isConsumed();
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (getCurrentScene() == null) return false;
        MouseButtonEvent event = new MouseButtonEvent(mouseX, mouseY, button);
        getCurrentScene().handleEvent(ScreenEventType.MOUSE_RELEASED, event);
        return event.isConsumed();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (getCurrentScene() == null) return false;
        MouseDragEvent event = new MouseDragEvent(mouseX, mouseY, button, deltaX, deltaY);
        getCurrentScene().handleEvent(ScreenEventType.MOUSE_DRAGGED, event);
        return event.isConsumed();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (getCurrentScene() == null) return false;
        MouseScrollEvent event = new MouseScrollEvent(mouseX, mouseY, amount);
        getCurrentScene().handleEvent(ScreenEventType.MOUSE_SCOLLED, event);
        return event.isConsumed();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (getCurrentScene() == null) return false;
        if (keyCode == 256) {
            hide();
            return true;
        }
        KeyEvent event = new KeyEvent(keyCode, scanCode, modifiers);
        getCurrentScene().handleEvent(ScreenEventType.KEY_PRESSED, event);
        return event.isConsumed();
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (getCurrentScene() == null) return false;
        KeyEvent event = new KeyEvent(keyCode, scanCode, modifiers);
        getCurrentScene().handleEvent(ScreenEventType.KEY_RELEASED, event);
        return event.isConsumed();
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (getCurrentScene() == null) return false;
        TypeEvent event = new TypeEvent(chr, modifiers);
        getCurrentScene().handleEvent(ScreenEventType.CHAR_TYPED, event);
        return event.isConsumed();
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        if (getCurrentScene() == null) return;
        getCurrentScene().handleEvent(ScreenEventType.MOUSE_MOVED, new MouseEvent(mouseX, mouseY));
    }
}
