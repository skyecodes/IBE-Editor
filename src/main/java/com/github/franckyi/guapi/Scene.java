package com.github.franckyi.guapi;

import com.github.franckyi.guapi.event.IEventListener;
import com.github.franckyi.guapi.node.TextFieldBase;
import com.github.franckyi.guapi.scene.IBackground;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class Scene implements IScreenEventListener, IParent {

    private final Set<IEventListener<GuiScreenEvent.MouseClickedEvent>> onMouseClickedListeners;
    private final Set<IEventListener<GuiScreenEvent.MouseReleasedEvent>> onMouseReleasedListeners;
    private final Set<IEventListener<GuiScreenEvent.MouseDragEvent>> onMouseDraggedListeners;
    private final Set<IEventListener<GuiScreenEvent.MouseScrollEvent>> onMouseScrolledListeners;
    private final Set<IEventListener<GuiScreenEvent.KeyboardKeyPressedEvent>> onKeyPressedListeners;
    private final Set<IEventListener<GuiScreenEvent.KeyboardKeyReleasedEvent>> onKeyReleasedListeners;
    private final Set<IEventListener<GuiScreenEvent.KeyboardCharTypedEvent>> onCharTypedListeners;
    private final Set<IEventListener<GuiScreenEvent.InitGuiEvent>> onInitGuiListeners;
    private final Set<IEventListener<GuiScreenEvent.DrawScreenEvent>> onDrawScreenListeners;
    private final Set<IEventListener<GuiScreenEvent.BackgroundDrawnEvent>> onBackgroundDrawnListeners;
    private final Set<IEventListener<GuiScreenEvent.PotionShiftEvent>> onPotionShiftListeners;
    private final Set<IEventListener<GuiScreenEvent.ActionPerformedEvent>> onActionPerformedListeners;
    private final Set<Runnable> onGuiClosedListeners;
    private final Set<Runnable> onResizeListeners;
    private final Set<Runnable> onTickListeners;

    private final Screen screen;
    private GuiScreen oldScreen;
    private Node content;

    private boolean guiPauseGame;
    private boolean guiCloseWithEscape;
    private IBackground background;

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
        background = IBackground.DEFAULT;
        onMouseClickedListeners = new HashSet<>();
        onMouseReleasedListeners = new HashSet<>();
        onMouseDraggedListeners = new HashSet<>();
        onMouseScrolledListeners = new HashSet<>();
        onKeyPressedListeners = new HashSet<>();
        onKeyReleasedListeners = new HashSet<>();
        onCharTypedListeners = new HashSet<>();
        onInitGuiListeners = new HashSet<>();
        onDrawScreenListeners = new HashSet<>();
        onBackgroundDrawnListeners = new HashSet<>();
        onPotionShiftListeners = new HashSet<>();
        onActionPerformedListeners = new HashSet<>();
        onGuiClosedListeners = new HashSet<>();
        onResizeListeners = new HashSet<>();
        onTickListeners = new HashSet<>();
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

    public IBackground getBackground() {
        return background;
    }

    public void setBackground(IBackground background) {
        this.background = background;
    }

    public Set<IEventListener<GuiScreenEvent.MouseClickedEvent>> getOnMouseClickedListeners() {
        return onMouseClickedListeners;
    }

    public Set<IEventListener<GuiScreenEvent.MouseReleasedEvent>> getOnMouseReleasedListeners() {
        return onMouseReleasedListeners;
    }

    public Set<IEventListener<GuiScreenEvent.MouseDragEvent>> getOnMouseDraggedListeners() {
        return onMouseDraggedListeners;
    }

    public Set<IEventListener<GuiScreenEvent.MouseScrollEvent>> getOnMouseScrolledListeners() {
        return onMouseScrolledListeners;
    }

    public Set<IEventListener<GuiScreenEvent.KeyboardKeyPressedEvent>> getOnKeyPressedListeners() {
        return onKeyPressedListeners;
    }

    public Set<IEventListener<GuiScreenEvent.KeyboardKeyReleasedEvent>> getOnKeyReleasedListeners() {
        return onKeyReleasedListeners;
    }

    public Set<IEventListener<GuiScreenEvent.KeyboardCharTypedEvent>> getOnCharTypedListeners() {
        return onCharTypedListeners;
    }

    public Set<IEventListener<GuiScreenEvent.InitGuiEvent>> getOnInitGuiListeners() {
        return onInitGuiListeners;
    }

    public Set<IEventListener<GuiScreenEvent.DrawScreenEvent>> getOnDrawScreenListeners() {
        return onDrawScreenListeners;
    }

    public Set<IEventListener<GuiScreenEvent.BackgroundDrawnEvent>> getOnBackgroundDrawnListeners() {
        return onBackgroundDrawnListeners;
    }

    public Set<IEventListener<GuiScreenEvent.PotionShiftEvent>> getOnPotionShiftListeners() {
        return onPotionShiftListeners;
    }

    public Set<Runnable> getOnGuiClosedListeners() {
        return onGuiClosedListeners;
    }

    public Set<Runnable> getOnResizeListeners() {
        return onResizeListeners;
    }

    public Set<Runnable> getOnTickListeners() {
        return onTickListeners;
    }

    /**
     * @deprecated The ActionPerformedEvent is never triggered, adding a listener won't do anything
     */
    @Deprecated
    public Set<IEventListener<GuiScreenEvent.ActionPerformedEvent>> getOnActionPerformedListeners() {
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
        return IScreenEventListener.super.onKeyPressed(event);
    }

    protected void onGuiClosed() {
        this.getOnGuiClosedListeners().forEach(Runnable::run);
    }

    protected void onResize() {
        this.getOnResizeListeners().forEach(Runnable::run);
    }

    protected void onTick() {
        this.getOnTickListeners().forEach(Runnable::run);
        if (content instanceof IParent) {
            this.tick((IParent) content);
        }
    }

    private void tick(IParent parent) {
        for (IScreenEventListener e : parent.getChildren()) {
            if (e instanceof TextFieldBase) {
                ((TextFieldBase) e).tick();
            } else if (e instanceof IParent) {
                tick((IParent) e);
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
    public List<IScreenEventListener> getChildren() {
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
        private final Set<Tooltip> tooltips;

        public Screen(Scene scene) {
            this.scene = scene;
            tooltips = new HashSet<>();
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
            RenderHelper.enableGUIStandardItemLighting();
            this.getScene().render(mouseX, mouseY, partialTicks);
            tooltips.forEach(tooltip -> super.drawHoveringText(tooltip.textLines, tooltip.x, tooltip.y, tooltip.font));
            tooltips.clear();
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
        public void renderToolTip(ItemStack stack, int x, int y) {
            super.renderToolTip(stack, x, y);
        }

        @Override
        public void drawHoveringText(String text, int x, int y) {
            tooltips.add(new Tooltip(Collections.singletonList(text), x, y, fontRenderer));
        }

        @Override
        public void drawHoveringText(List<String> textLines, int x, int y) {
            tooltips.add(new Tooltip(textLines, x, y, fontRenderer));
        }

        @Override
        public void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font) {
            tooltips.add(new Tooltip(textLines, x, y, font));
        }

        private class Tooltip {

            private final List<String> textLines;
            private final int x;
            private final int y;
            private final FontRenderer font;

            private Tooltip(List<String> textLines, int x, int y, FontRenderer font) {
                this.textLines = textLines;
                this.x = x;
                this.y = y;
                this.font = font;
            }
        }

    }

}
