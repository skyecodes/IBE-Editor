package com.github.franckyi.guapi;

import com.github.franckyi.guapi.event.EventHandler;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.guapi.scene.Background;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;
import java.util.function.BiConsumer;

public class Scene implements ScreenEventListener, Parent {

    private final List<EventHandler<GuiScreenEvent.MouseClickedEvent>> onMouseClickedListeners;
    private final List<EventHandler<GuiScreenEvent.MouseReleasedEvent>> onMouseReleasedListeners;
    private final List<EventHandler<GuiScreenEvent.MouseDragEvent>> onMouseDraggedListeners;
    private final List<EventHandler<GuiScreenEvent.MouseScrollEvent>> onMouseScrolledListeners;
    private final List<EventHandler<GuiScreenEvent.KeyboardKeyPressedEvent>> onKeyPressedListeners;
    private final List<EventHandler<GuiScreenEvent.KeyboardKeyReleasedEvent>> onKeyReleasedListeners;
    private final List<EventHandler<GuiScreenEvent.KeyboardCharTypedEvent>> onCharTypedListeners;
    private final List<EventHandler<GuiScreenEvent.InitGuiEvent>> onInitGuiListeners;
    private final List<EventHandler<GuiScreenEvent.DrawScreenEvent>> onDrawScreenListeners;
    private final List<EventHandler<GuiScreenEvent.BackgroundDrawnEvent>> onBackgroundDrawnListeners;
    private final List<EventHandler<GuiScreenEvent.PotionShiftEvent>> onPotionShiftListeners;
    private final List<EventHandler<GuiScreenEvent.ActionPerformedEvent>> onActionPerformedListeners;
    private final List<Runnable> onGuiClosedListeners;
    private final List<Runnable> onResizeListeners;
    private final List<Runnable> onTickListeners;

    private final Screen screen;
    private GuiScreen oldScreen;
    private Node content;

    private boolean guiPauseGame;
    private boolean guiCloseWithEscape;
    private Background background;

    public Scene() {
        this(null);
    }

    public Scene(Node content) {
        screen = new Screen(this);
        if (content != null) {
            this.content = content;
            content.setParent(this);
        }
        guiPauseGame = true;
        guiCloseWithEscape = true;
        background = Background.DEFAULT;
        onMouseClickedListeners = new ArrayList<>();
        onMouseReleasedListeners = new ArrayList<>();
        onMouseDraggedListeners = new ArrayList<>();
        onMouseScrolledListeners = new ArrayList<>();
        onKeyPressedListeners = new ArrayList<>();
        onKeyReleasedListeners = new ArrayList<>();
        onCharTypedListeners = new ArrayList<>();
        onInitGuiListeners = new ArrayList<>();
        onDrawScreenListeners = new ArrayList<>();
        onBackgroundDrawnListeners = new ArrayList<>();
        onPotionShiftListeners = new ArrayList<>();
        onActionPerformedListeners = new ArrayList<>();
        onGuiClosedListeners = new ArrayList<>();
        onResizeListeners = new ArrayList<>();
        onTickListeners = new ArrayList<>();
    }

    public Screen getScreen() {
        return screen;
    }

    public Node getContent() {
        return content;
    }

    public void setContent(Node content) {
        if (this.getContent() != null) {
            this.getContent().setParent(null);
        }
        this.content = content;
        if (content != null) {
            content.setParent(this);
        }
    }

    public boolean doesGuiPauseGame() {
        return guiPauseGame;
    }

    public void setGuiPauseGame(boolean guiPauseGame) {
        this.guiPauseGame = guiPauseGame;
    }

    public boolean doesGuiCloseWithEscape() {
        return guiCloseWithEscape;
    }

    public void setGuiCloseWithEscape(boolean guiCloseWithEscape) {
        this.guiCloseWithEscape = guiCloseWithEscape;
    }

    public boolean isContentFullScreen() {
        return this.getContent() != null &&
                this.getContent().getPrefWidth() == this.getScreen().width - this.getContent().getMargin().getHorizontal() &&
                this.getContent().getPrefHeight() == this.getScreen().height - this.getContent().getMargin().getVertical();
    }

    public void setContentFullScreen() {
        if (this.getContent() != null) {
            this.getContent().setPrefSize(
                    this.getScreen().width - this.getContent().getMargin().getHorizontal(),
                    this.getScreen().height - this.getContent().getMargin().getVertical()
            );
        }
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public List<EventHandler<GuiScreenEvent.MouseClickedEvent>> getOnMouseClickedListeners() {
        return onMouseClickedListeners;
    }

    public List<EventHandler<GuiScreenEvent.MouseReleasedEvent>> getOnMouseReleasedListeners() {
        return onMouseReleasedListeners;
    }

    public List<EventHandler<GuiScreenEvent.MouseDragEvent>> getOnMouseDraggedListeners() {
        return onMouseDraggedListeners;
    }

    public List<EventHandler<GuiScreenEvent.MouseScrollEvent>> getOnMouseScrolledListeners() {
        return onMouseScrolledListeners;
    }

    public List<EventHandler<GuiScreenEvent.KeyboardKeyPressedEvent>> getOnKeyPressedListeners() {
        return onKeyPressedListeners;
    }

    public List<EventHandler<GuiScreenEvent.KeyboardKeyReleasedEvent>> getOnKeyReleasedListeners() {
        return onKeyReleasedListeners;
    }

    public List<EventHandler<GuiScreenEvent.KeyboardCharTypedEvent>> getOnCharTypedListeners() {
        return onCharTypedListeners;
    }

    public List<EventHandler<GuiScreenEvent.InitGuiEvent>> getOnInitGuiListeners() {
        return onInitGuiListeners;
    }

    public List<EventHandler<GuiScreenEvent.DrawScreenEvent>> getOnDrawScreenListeners() {
        return onDrawScreenListeners;
    }

    public List<EventHandler<GuiScreenEvent.BackgroundDrawnEvent>> getOnBackgroundDrawnListeners() {
        return onBackgroundDrawnListeners;
    }

    public List<EventHandler<GuiScreenEvent.PotionShiftEvent>> getOnPotionShiftListeners() {
        return onPotionShiftListeners;
    }

    public List<Runnable> getOnGuiClosedListeners() {
        return onGuiClosedListeners;
    }

    public List<Runnable> getOnResizeListeners() {
        return onResizeListeners;
    }

    public List<Runnable> getOnTickListeners() {
        return onTickListeners;
    }

    /**
     * @deprecated The ActionPerformedEvent is never triggered, adding a listener won't do anything
     */
    @Deprecated
    public List<EventHandler<GuiScreenEvent.ActionPerformedEvent>> getOnActionPerformedListeners() {
        return onActionPerformedListeners;
    }

    protected void onInitGui(GuiScreenEvent.InitGuiEvent event) {
        this.getOnInitGuiListeners().forEach(listener -> listener.handle(event));
    }

    protected void onDrawScreen(GuiScreenEvent.DrawScreenEvent event) {
        this.getOnDrawScreenListeners().forEach(listener -> listener.handle(event));
    }

    protected void onBackgroundDrawn(GuiScreenEvent.BackgroundDrawnEvent event) {
        this.getOnBackgroundDrawnListeners().forEach(listener -> listener.handle(event));
    }

    protected void onPotionShift(GuiScreenEvent.PotionShiftEvent event) {
        this.getOnPotionShiftListeners().forEach(listener -> listener.handle(event));
    }

    @Deprecated
    protected void onActionPerformed(GuiScreenEvent.ActionPerformedEvent event) {
        this.getOnActionPerformedListeners().forEach(listener -> listener.handle(event));
    }

    @Override
    public boolean onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent event) {
        this.getScreen().keyPressed(event.getKeyCode(), event.getScanCode(), event.getModifiers());
        return ScreenEventListener.super.onKeyPressed(event);
    }

    protected void onGuiClosed() {
        this.getOnGuiClosedListeners().forEach(Runnable::run);
    }

    protected void onResize() {
        this.getOnResizeListeners().forEach(Runnable::run);
    }

    protected void onTick() {
        this.getOnTickListeners().forEach(Runnable::run);
        if (content instanceof Parent) {
            this.tick((Parent) content);
        }
    }

    private void tick(Parent parent) {
        for (ScreenEventListener e : parent.getChildren()) {
            if (e instanceof TextField) {
                ((TextField) e).tick();
            } else if (e instanceof Parent) {
                tick((Parent) e);
            }
        }
    }

    public void show() {
        oldScreen = mc.currentScreen;
        mc.displayGuiScreen(screen);
    }

    public void close() {
        mc.displayGuiScreen(oldScreen);
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.getContent() != null && this.getContent().isVisible())
            this.getContent().render(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateChildrenPos() {
        if (this.getContent() != null) {
            this.getContent().setPosition(content.getMargin().getLeft(), content.getMargin().getTop());
        }
    }

    @Override
    public List<ScreenEventListener> getChildren() {
        return Collections.singletonList(content);
    }

    public static class ScreenEventHandler {

        @SubscribeEvent
        public static void onInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
            handle(event, Scene::onInitGui);
        }

        @SubscribeEvent
        public static void onDrawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
            handle(event, Scene::onDrawScreen);
        }

        @SubscribeEvent
        public static void onBackgroundDrawn(GuiScreenEvent.BackgroundDrawnEvent event) {
            handle(event, Scene::onBackgroundDrawn);
        }

        @SubscribeEvent
        public static void onPotionShift(GuiScreenEvent.PotionShiftEvent event) {
            handle(event, Scene::onPotionShift);
        }

        @SubscribeEvent
        @Deprecated
        public static void onActionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {
            handle(event, Scene::onActionPerformed);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onMouseClicked(GuiScreenEvent.MouseClickedEvent.Pre event) {
            handle(event, Scene::onMouseClicked);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onMouseReleased(GuiScreenEvent.MouseReleasedEvent.Pre event) {
            handle(event, Scene::onMouseReleased);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onMouseDragged(GuiScreenEvent.MouseDragEvent.Pre event) {
            handle(event, Scene::onMouseDragged);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onMouseScrolled(GuiScreenEvent.MouseScrollEvent.Pre event) {
            handle(event, Scene::onMouseScrolled);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onKeyboardKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre event) {
            handle(event, Scene::onKeyPressed);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onKeyboardKeyReleased(GuiScreenEvent.KeyboardKeyReleasedEvent.Pre event) {
            handle(event, Scene::onKeyReleased);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onKeyboardCharTyped(GuiScreenEvent.KeyboardCharTypedEvent.Pre event) {
            handle(event, Scene::onCharTyped);
        }

        private static <T extends GuiScreenEvent> void handle(T event, BiConsumer<Scene, T> eh) {
            if (event != null && event.getGui() instanceof Screen) {
                Scene scene = ((Screen) event.getGui()).getScene();
                if (scene != null) {
                    eh.accept(scene, event);
                }
            }
        }

    }

    public class Screen extends GuiScreen {

        private final Scene scene;
        private final Set<Runnable> postRender;

        public Screen(Scene scene) {
            this.scene = scene;
            this.postRender = new HashSet<>();
        }

        public Scene getScene() {
            return scene;
        }

        @Override
        public void setWorldAndResolution(Minecraft mc, int width, int height) {
            boolean flag = this.getScene().isContentFullScreen();
            super.setWorldAndResolution(mc, width, height);
            if (flag) {
                this.getScene().setContentFullScreen();
            } else {
                this.getScene().updateChildrenPos();
            }
        }

        @Override
        public void render(int mouseX, int mouseY, float partialTicks) {
            this.getScene().getBackground().draw(this);
            this.getScene().render(mouseX, mouseY, partialTicks);
            postRender.forEach(Runnable::run);
            postRender.clear();
        }

        @Override
        public boolean allowCloseWithEscape() {
            return this.getScene().doesGuiCloseWithEscape();
        }

        @Override
        public boolean doesGuiPauseGame() {
            return this.getScene().doesGuiPauseGame();
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if (keyCode == 256 && this.allowCloseWithEscape()) {
                this.close();
                return true;
            }
            return false;
        }

        @Override
        public void onGuiClosed() {
            this.getScene().onGuiClosed();
        }

        @Override
        public void onResize(Minecraft mcIn, int w, int h) {
            super.onResize(mcIn, w, h);
            this.getScene().onResize();
        }

        @Override
        public void tick() {
            this.getScene().onTick();
        }

        @Override
        public void drawHoveringText(String text, int x, int y) {
            postRender.add(() -> super.drawHoveringText(text, x, y));
        }
    }

}
