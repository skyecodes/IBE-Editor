package com.github.franckyi.guapi.common.hooks;

import com.github.franckyi.guapi.common.Scene;
import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.data.Property;
import com.github.franckyi.guapi.common.event.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public abstract class ScreenHandlerBase<T> implements ScreenHandler {
    private final Queue<Scene> scenes = new LinkedList<>();
    private final Property<Scene> currentSceneProperty = new ObjectProperty<>();
    private T screen;

    public ScreenHandlerBase() {
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
        return currentSceneProperty.getValue();
    }

    public Property<Scene> currentSceneProperty() {
        return currentSceneProperty;
    }

    public void setCurrentScene(Scene value) {
        currentSceneProperty.setValue(value);
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
        return currentSceneProperty().mapIfPresent(scene -> scene.mouseClicked(new MouseButtonEvent(mouseX, mouseY, button)), false);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return currentSceneProperty().mapIfPresent(scene -> scene.mouseReleased(new MouseButtonEvent(mouseX, mouseY, button)), false);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return currentSceneProperty().mapIfPresent(scene -> scene.mouseDragged(new MouseDragEvent(mouseX, mouseY, button, deltaX, deltaY)), false);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return currentSceneProperty().mapIfPresent(scene -> scene.mouseScrolled(new MouseScrollEvent(mouseX, mouseY, amount)), false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (getCurrentScene() == null) return false;
        if (keyCode == 256 && shouldCloseOnEsc()) {
            hide();
            return true;
        }
        return getCurrentScene().keyPressed(new KeyEvent(keyCode, scanCode, modifiers));
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return currentSceneProperty().mapIfPresent(scene -> scene.keyReleased(new KeyEvent(keyCode, scanCode, modifiers)), false);
    }

    @Override
    public boolean charTyped(char chr, int keyCode) {
        return currentSceneProperty().mapIfPresent(scene -> scene.charTyped(new TypeEvent(chr, keyCode)), false);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        currentSceneProperty().doIfPresent(scene -> scene.mouseMoved(new MouseEvent(mouseX, mouseY)));
    }

    public boolean shouldCloseOnEsc() {
        return true;
    }
}
