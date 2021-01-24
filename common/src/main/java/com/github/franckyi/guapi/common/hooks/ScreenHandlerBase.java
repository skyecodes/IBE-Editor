package com.github.franckyi.guapi.common.hooks;

import com.github.franckyi.guapi.common.Scene;
import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.data.Property;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public abstract class ScreenHandlerBase<T> implements ScreenHandler {
    private final Queue<Scene> scenes = new LinkedList<>();
    private final ObjectProperty<Scene> currentSceneProperty = new ObjectProperty<>();
    private T screen;

    public ScreenHandlerBase() {
        currentSceneProperty.addListener((oldVal, newVal) -> {
            if (newVal == null) {
                close();
            } else {
                newVal.onShow();
                if (oldVal == null) {
                    open();
                } else {
                    oldVal.onHide();
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
}
