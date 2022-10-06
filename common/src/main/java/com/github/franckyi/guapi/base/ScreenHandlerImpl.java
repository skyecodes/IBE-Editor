package com.github.franckyi.guapi.base;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.GuapiHelper;
import com.github.franckyi.guapi.api.ScreenHandler;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import com.github.franckyi.guapi.base.event.*;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ScreenHandlerImpl implements ScreenHandler {
    public static final ScreenHandler INSTANCE = new ScreenHandlerImpl();
    private final Deque<Scene> scenes = new ArrayDeque<>();
    private final ObjectProperty<Scene> currentSceneProperty = ObjectProperty.create();
    private final IntegerProperty widthProperty = IntegerProperty.create();
    private final IntegerProperty heightProperty = IntegerProperty.create();
    private final Screen screen = new GuapiScreen();
    private Screen oldScreen;

    private ScreenHandlerImpl() {
        currentSceneProperty().addListener((oldVal, newVal) -> {
            if (newVal == null) {
                if (oldVal != null) {
                    oldVal.widthProperty().unbind();
                    oldVal.heightProperty().unbind();
                    oldVal.hide();
                }
                closeScreen();
            } else {
                if (oldVal == null) {
                    openScreen();
                } else {
                    oldVal.hide();
                }
                newVal.widthProperty().unbind();
                newVal.widthProperty().bind(widthProperty);
                newVal.heightProperty().unbind();
                newVal.heightProperty().bind(heightProperty);
                newVal.show();
            }
        });
    }

    @Override
    public void showScene(Scene scene) {
        checkScreen();
        scenes.push(scene);
        setCurrentScene(scene);
    }

    @Override
    public void replaceScene(Scene scene) {
        checkScreen();
        if (!scenes.isEmpty()) {
            scenes.pop();
        }
        showScene(scene);
    }

    @Override
    public void hideScene() {
        if (!scenes.isEmpty()) {
            scenes.pop();
            setCurrentScene(scenes.peek());
        }
    }

    @Override
    public Screen getGuapiScreen() {
        return screen;
    }

    private void checkScreen() {
        if (Minecraft.getInstance().screen != screen) {
            scenes.clear();
            setCurrentScene(null);
        }
    }

    private Scene getCurrentScene() {
        return currentSceneProperty().getValue();
    }

    private ObjectProperty<Scene> currentSceneProperty() {
        return currentSceneProperty;
    }

    private void setCurrentScene(Scene value) {
        currentSceneProperty().setValue(value);
    }

    private void openScreen() {
        oldScreen = Minecraft.getInstance().screen;
        Minecraft.getInstance().setScreen(screen);
    }

    private void closeScreen() {
        Minecraft.getInstance().setScreen(oldScreen);
    }

    private void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        try {
            getCurrentScene().render(matrices, mouseX, mouseY, delta);
        } catch (Exception e) {
            Guapi.getExceptionHandler().handleRenderException(e, matrices, mouseX, mouseY, delta, getCurrentScene());
            Guapi.getDefaultLogger().error(Guapi.LOG_MARKER, "Error while rendering GUAPI Scene", e);
            hideScene();
        }
    }

    private void tick() {
        if (currentSceneProperty().hasValue()) {
            try {
                getCurrentScene().tick();
            } catch (Exception e) {
                Guapi.getExceptionHandler().handleTickException(e, getCurrentScene());
                Guapi.getDefaultLogger().error(Guapi.LOG_MARKER, "Error while ticking GUAPI Scene", e);
                hideScene();
            }
        }
    }

    private void updateSize(int width, int height) {
        widthProperty.setValue(width);
        heightProperty.setValue(height);
    }

    private boolean mouseClicked(double mouseX, double mouseY, int button) {
        return handleEvent(ScreenEventType.MOUSE_CLICKED, new MouseButtonEventImpl(mouseX, mouseY, button));
    }

    private boolean mouseReleased(double mouseX, double mouseY, int button) {
        return handleEvent(ScreenEventType.MOUSE_RELEASED, new MouseButtonEventImpl(mouseX, mouseY, button));
    }

    private boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return handleEvent(ScreenEventType.MOUSE_DRAGGED, new MouseDragEventImpl(mouseX, mouseY, button, deltaX, deltaY));
    }

    private boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return handleEvent(ScreenEventType.MOUSE_SCOLLED, new MouseScrollEventImpl(mouseX, mouseY, amount));
    }

    private boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return handleEvent(ScreenEventType.KEY_PRESSED, new KeyEventImpl(keyCode, scanCode, modifiers));
    }

    private boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return handleEvent(ScreenEventType.KEY_RELEASED, new KeyEventImpl(keyCode, scanCode, modifiers));
    }

    private boolean charTyped(char chr, int modifiers) {
        return handleEvent(ScreenEventType.CHAR_TYPED, new TypeEventImpl(chr, modifiers));
    }

    private void mouseMoved(double mouseX, double mouseY) {
        handleEvent(ScreenEventType.MOUSE_MOVED, new MouseEventImpl(mouseX, mouseY));
    }

    private <E extends ScreenEvent> boolean handleEvent(ScreenEventType<E> type, E event) {
        if (!currentSceneProperty().hasValue()) return false;
        try {
            getCurrentScene().handleEvent(type, event);
        } catch (Exception e) {
            Guapi.getExceptionHandler().handleEventException(e, type, event, getCurrentScene());
            Guapi.getDefaultLogger().error(Guapi.LOG_MARKER, "Error while handling " + type.getName() + " event on GUAPI Scene", e);
            hideScene();
        }
        return event.isConsumed();
    }

    private final class GuapiScreen extends Screen {
        private GuapiScreen() {
            super(GuapiHelper.EMPTY_TEXT);
        }

        @Override
        public void render(@NotNull PoseStack matrices, int mouseX, int mouseY, float partialTicks) {
            if (!currentSceneProperty().hasValue()) return;
            if (getCurrentScene().isTexturedBackground()) {
                renderDirtBackground(0);
            } else {
                renderBackground(matrices);
            }
            ScreenHandlerImpl.this.render(matrices, mouseX, mouseY, partialTicks);
        }

        @Override
        public void tick() {
            ScreenHandlerImpl.this.tick();
        }

        @Override
        protected void init() {
            ScreenHandlerImpl.this.updateSize(width, height);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            return ScreenHandlerImpl.this.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public boolean mouseReleased(double mouseX, double mouseY, int button) {
            return ScreenHandlerImpl.this.mouseReleased(mouseX, mouseY, button);
        }

        @Override
        public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
            return ScreenHandlerImpl.this.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }

        @Override
        public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
            return ScreenHandlerImpl.this.mouseScrolled(mouseX, mouseY, amount);
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            return ScreenHandlerImpl.this.keyPressed(keyCode, scanCode, modifiers);
        }

        @Override
        public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
            return ScreenHandlerImpl.this.keyReleased(keyCode, scanCode, modifiers);
        }

        @Override
        public boolean charTyped(char chr, int modifiers) {
            return ScreenHandlerImpl.this.charTyped(chr, modifiers);
        }

        @Override
        public void mouseMoved(double mouseX, double mouseY) {
            ScreenHandlerImpl.this.mouseMoved(mouseX, mouseY);
        }
    }
}
