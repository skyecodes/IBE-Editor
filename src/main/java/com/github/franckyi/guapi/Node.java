package com.github.franckyi.guapi;

import com.github.franckyi.guapi.event.EventHandler;
import com.github.franckyi.guapi.math.Insets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class Node<V extends Node.GuiView> implements ScreenEventListener {

    public static final int COMPUTED_SIZE = -1;

    private final List<EventHandler<GuiScreenEvent.MouseClickedEvent>> onMouseClickedListeners;
    private final List<EventHandler<GuiScreenEvent.MouseReleasedEvent>> onMouseReleasedListeners;
    private final List<EventHandler<GuiScreenEvent.MouseDragEvent>> onMouseDraggedListeners;
    private final List<EventHandler<GuiScreenEvent.MouseScrollEvent>> onMouseScrolledListeners;
    private final List<EventHandler<GuiScreenEvent.KeyboardKeyPressedEvent>> onKeyPressedListeners;
    private final List<EventHandler<GuiScreenEvent.KeyboardKeyReleasedEvent>> onKeyReleasedListeners;
    private final List<EventHandler<GuiScreenEvent.KeyboardCharTypedEvent>> onCharTypedListeners;

    private final V view;
    private Parent parent;
    private int computedWidth;
    private int computedHeight;
    private int prefWidth;
    private int prefHeight;
    private Insets padding;
    private Insets margin;

    public Node(V view) {
        this.view = view;
        parent = null;
        computedWidth = 0;
        computedHeight = 0;
        prefWidth = COMPUTED_SIZE;
        prefHeight = COMPUTED_SIZE;
        padding = Insets.NONE;
        margin = Insets.NONE;
        onMouseClickedListeners = new ArrayList<>();
        onMouseReleasedListeners = new ArrayList<>();
        onMouseDraggedListeners = new ArrayList<>();
        onMouseScrolledListeners = new ArrayList<>();
        onKeyPressedListeners = new ArrayList<>();
        onKeyReleasedListeners = new ArrayList<>();
        onCharTypedListeners = new ArrayList<>();
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
        if (parent != null) {
            parent.updateChildrenPos();
        }
    }

    public Scene getScene() {
        GuiScreen screen = Minecraft.getInstance().currentScreen;
        if (screen instanceof Scene.Screen) {
            return ((Scene.Screen) screen).getScene();
        }
        return null;
    }

    public int getX() {
        return this.getView().getX();
    }

    public void setX(int x) {
        this.getView().setX(x);
    }

    public int getY() {
        return this.getView().getY();
    }

    public void setY(int y) {
        this.getView().setY(y);
    }

    public void setPosition(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getWidth() {
        return this.getView().getWidth();
    }

    protected void setWidth(int width) {
        if (this.getWidth() != width) {
            this.getView().setWidth(width);
            if (this.getParent() != null) {
                this.getParent().updateChildrenPos();
            }
        }
    }

    public int getHeight() {
        return this.getView().getHeight();
    }

    protected void setHeight(int height) {
        if (this.getHeight() != height) {
            this.getView().setHeight(height);
            if (this.getParent() != null) {
                this.getParent().updateChildrenPos();
            }
        }
    }

    protected void setSize(int width, int height) {
        boolean flag = false;
        if (this.getWidth() != width) {
            this.getView().setWidth(width);
            flag = true;
        }
        if (this.getHeight() != height) {
            this.getView().setHeight(height);
            flag = true;
        }
        if (flag && this.getParent() != null) {
            this.getParent().updateChildrenPos();
        }
    }

    public int getComputedWidth() {
        return computedWidth;
    }

    public void setComputedWidth(int computedWidth) {
        this.computedWidth = computedWidth;
    }

    public int getComputedHeight() {
        return computedHeight;
    }

    public void setComputedHeight(int computedHeight) {
        this.computedHeight = computedHeight;
    }

    public int getPrefWidth() {
        return prefWidth;
    }

    public void setPrefWidth(int prefWidth) {
        if (this.getPrefWidth() != prefWidth) {
            this.prefWidth = prefWidth;
            if (this.getPrefWidth() == COMPUTED_SIZE)
                this.computeWidth();
            this.updateWidth();
        }

    }

    public int getPrefHeight() {
        return prefHeight;
    }

    public void setPrefHeight(int prefHeight) {
        if (this.getPrefHeight() != prefHeight) {
            this.prefHeight = prefHeight;
            if (this.getPrefHeight() == COMPUTED_SIZE)
                this.computeHeight();
            this.updateHeight();
        }
    }

    public void setPrefSize(int prefWidth, int prefHeight) {
        if (this.getPrefHeight() != prefHeight) {
            this.prefHeight = prefHeight;
            if (this.getPrefHeight() == COMPUTED_SIZE)
                this.computeHeight();
        }
        if (this.getPrefWidth() != prefWidth) {
            this.prefWidth = prefWidth;
            if (this.getPrefWidth() == COMPUTED_SIZE)
                this.computeWidth();
        }
        this.updateSize();
    }

    public Insets getPadding() {
        return padding;
    }

    public void setPadding(Insets padding) {
        if (!this.getPadding().equals(padding)) {
            this.padding = padding;
            this.computeSize();
            this.updateSize();
        }
    }

    public Insets getMargin() {
        return margin;
    }

    public void setMargin(Insets margin) {
        if (!this.getMargin().equals(margin)) {
            boolean flag = false;
            if (this.getParent() instanceof Scene) {
                Scene scene = (Scene) this.getParent();
                if (scene.isContentFullScreen()) {
                    flag = true;
                }
            }
            this.margin = margin;
            if (flag) {
                ((Scene) this.getParent()).setContentFullScreen();
            }
            if (this.getParent() != null) {
                this.getParent().updateChildrenPos();
            }
        }
    }

    public boolean isVisible() {
        return this.getView().isVisible();
    }

    public void setVisible(boolean visible) {
        this.getView().setVisible(visible);
    }

    public V getView() {
        return view;
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

    @Override
    public boolean onMouseClicked(GuiScreenEvent.MouseClickedEvent event) {
        if (this.getView().isVisible() && this.getView().mouseClicked(event.getMouseX(), event.getMouseY(), event.getButton())) {
            ScreenEventListener.super.onMouseClicked(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMouseReleased(GuiScreenEvent.MouseReleasedEvent event) {
        if (this.getView().isVisible() && this.getView().mouseReleased(event.getMouseX(), event.getMouseY(), event.getButton())) {
            ScreenEventListener.super.onMouseReleased(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMouseDragged(GuiScreenEvent.MouseDragEvent event) {
        if (this.getView().isVisible() && this.getView().mouseDragged(event.getMouseX(), event.getMouseY(), event.getMouseButton(), event.getDragX(), event.getDragY())) {
            ScreenEventListener.super.onMouseDragged(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMouseScrolled(GuiScreenEvent.MouseScrollEvent event) {
        if (this.getView().isVisible() && this.getView().mouseScrolled(event.getScrollDelta())) {
            ScreenEventListener.super.onMouseScrolled(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent event) {
        if (this.getView().isVisible() && this.getView().keyPressed(event.getKeyCode(), event.getScanCode(), event.getModifiers())) {
            ScreenEventListener.super.onKeyPressed(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyReleased(GuiScreenEvent.KeyboardKeyReleasedEvent event) {
        if (this.getView().isVisible() && this.getView().keyReleased(event.getKeyCode(), event.getScanCode(), event.getModifiers())) {
            ScreenEventListener.super.onKeyReleased(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCharTyped(GuiScreenEvent.KeyboardCharTypedEvent event) {
        if (this.getView().isVisible() && this.getView().charTyped(event.getCodePoint(), event.getModifiers())) {
            ScreenEventListener.super.onCharTyped(event);
            return true;
        }
        return false;
    }

    public void updateSize() {
        this.setSize(this.getPrefWidth() == COMPUTED_SIZE ? this.getComputedWidth() : this.getPrefWidth(),
                this.getPrefHeight() == COMPUTED_SIZE ? this.getComputedHeight() : this.getPrefHeight());
    }

    protected void updateWidth() {
        this.setWidth(this.getPrefWidth() == COMPUTED_SIZE ? this.getComputedWidth() : this.getPrefWidth());
    }

    protected void updateHeight() {
        this.setHeight(this.getPrefHeight() == COMPUTED_SIZE ? this.getComputedHeight() : this.getPrefHeight());
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        this.getView().render(mouseX, mouseY, partialTicks);
    }

    public void computeSize() {
        this.computeWidth();
        this.computeHeight();
    }

    protected abstract void computeWidth();

    protected abstract void computeHeight();

    @Mod.EventBusSubscriber
    public static class NodeEventHandler {

        @SubscribeEvent
        public static void onMouseClicked(GuiScreenEvent.MouseClickedEvent.Pre event) {
            handle(event, ScreenEventListener::onMouseClicked);
        }

        @SubscribeEvent
        public static void onMouseReleased(GuiScreenEvent.MouseReleasedEvent.Pre event) {
            handle(event, ScreenEventListener::onMouseReleased);
        }

        @SubscribeEvent
        public static void onMouseDragged(GuiScreenEvent.MouseDragEvent.Pre event) {
            handle(event, ScreenEventListener::onMouseDragged);
        }

        @SubscribeEvent
        public static void onMouseScrolled(GuiScreenEvent.MouseScrollEvent.Pre event) {
            handle(event, ScreenEventListener::onMouseScrolled);
        }

        @SubscribeEvent
        public static void onKeyboardKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre event) {
            handle(event, ScreenEventListener::onKeyPressed);
        }

        @SubscribeEvent
        public static void onKeyboardKeyReleased(GuiScreenEvent.KeyboardKeyReleasedEvent.Pre event) {
            handle(event, ScreenEventListener::onKeyReleased);
        }

        @SubscribeEvent
        public static void onKeyboardCharTyped(GuiScreenEvent.KeyboardCharTypedEvent.Pre event) {
            handle(event, ScreenEventListener::onCharTyped);
        }

        private static <T extends GuiScreenEvent> void handle(T event, BiConsumer<ScreenEventListener, T> eh) {
            if (event != null && event.getGui() instanceof Scene.Screen) {
                Scene scene = ((Scene.Screen) event.getGui()).getScene();
                if (scene != null && scene.getContent() != null) {
                    propagate(scene.getContent(), event, eh);
                    event.setCanceled(true);
                }
            }
        }

        private static <T extends GuiScreenEvent> void propagate(ScreenEventListener node, T event, BiConsumer<ScreenEventListener, T> eh) {
            eh.accept(node, event);
            if (node instanceof Parent) {
                List<? extends ScreenEventListener> list = ((Parent) node).getChildren();
                for (int i = 0; i < list.size(); i++) {
                    propagate(list.get(i), event, eh);
                }
            }
        }
    }

    public interface GuiView extends IGuiEventListener {

        int getX();

        void setX(int x);

        int getY();

        void setY(int y);

        int getWidth();

        void setWidth(int width);

        int getHeight();

        void setHeight(int height);

        boolean isVisible();

        void setVisible(boolean visible);

        void render(int mouseX, int mouseY, float partialTicks);

        @Override
        default boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
            return this.inBounds(mouseX, mouseY);
        }

        @Override
        default boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
            return this.inBounds(mouseX, mouseY);
        }

        @Override
        default boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double deltaX, double deltaY) {
            return this.inBounds(mouseX, mouseY);
        }

        @Override
        default boolean mouseScrolled(double amount) {
            return false;
        }

        @Override
        default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            return false;
        }

        @Override
        default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
            return false;
        }

        @Override
        default boolean charTyped(char charTyped, int modifiers) {
            return false;
        }

        @Override
        default void focusChanged(boolean focused) {

        }

        @Override
        default boolean canFocus() {
            return false;
        }

        default boolean inBounds(double x, double y) {
            return x >= this.getX() && x <= this.getX() + this.getWidth() &&
                    y >= this.getY() && y <= this.getY() + this.getHeight();
        }

    }
}
