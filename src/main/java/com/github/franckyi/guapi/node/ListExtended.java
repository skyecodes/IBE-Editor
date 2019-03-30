package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Parent;
import com.github.franckyi.guapi.ScreenEventListener;
import com.github.franckyi.guapi.event.EventHandler;
import com.github.franckyi.guapi.math.Insets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListExtended<E extends GuiListExtended.IGuiListEntry<E> & ScreenEventListener> extends Node<ListExtended.GuiListExtendedView<E>> implements Parent {

    private int lazySlotHeight;

    public ListExtended(int slotHeight) {
        super(new GuiListExtendedView<>(slotHeight));
        this.lazySlotHeight = slotHeight;
        this.computeSize();
        this.updateSize();
    }

    public int getSlotHeight() {
        return lazySlotHeight;
    }

    public void setSlotHeight(int slotHeight) {
        if (lazySlotHeight != slotHeight) {
            try {
                this.getView().setSlotHeight(slotHeight);
                lazySlotHeight = slotHeight;
                this.computeHeight();
                this.updateHeight();
            } catch (ObfuscationReflectionHelper.UnableToFindFieldException | ObfuscationReflectionHelper.UnableToAccessFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public Insets getOffset() {
        return this.getView().getOffset();
    }

    public void setOffset(Insets offset) {
        this.getView().setOffset(offset);
    }

    public void setHasListHeader(boolean hasListHeaderIn, int headerPaddingIn) {
        this.getView().setHasListHeader(hasListHeaderIn, headerPaddingIn);
    }

    @Override
    public void updateChildrenPos() {
        this.computeSize();
        this.updateSize();
    }

    public List<E> getChildren() {
        return this.getView().getChildren();
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(200);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(200);
    }

    public static class NodeEntry<T extends Node> extends GuiListExtended.IGuiListEntry<NodeEntry<T>> implements ScreenEventListener, Parent {

        private final List<EventHandler<GuiScreenEvent.MouseClickedEvent>> onMouseClickedListeners;
        private final List<EventHandler<GuiScreenEvent.MouseReleasedEvent>> onMouseReleasedListeners;
        private final List<EventHandler<GuiScreenEvent.MouseDragEvent>> onMouseDraggedListeners;
        private final List<EventHandler<GuiScreenEvent.MouseScrollEvent>> onMouseScrolledListeners;
        private final List<EventHandler<GuiScreenEvent.KeyboardKeyPressedEvent>> onKeyPressedListeners;
        private final List<EventHandler<GuiScreenEvent.KeyboardKeyReleasedEvent>> onKeyReleasedListeners;
        private final List<EventHandler<GuiScreenEvent.KeyboardCharTypedEvent>> onCharTypedListeners;

        private final T node;

        public NodeEntry(T node) {
            this.node = node;
            node.setParent(this);
            onMouseClickedListeners = new ArrayList<>();
            onMouseReleasedListeners = new ArrayList<>();
            onMouseDraggedListeners = new ArrayList<>();
            onMouseScrolledListeners = new ArrayList<>();
            onKeyPressedListeners = new ArrayList<>();
            onKeyReleasedListeners = new ArrayList<>();
            onCharTypedListeners = new ArrayList<>();
        }

        public T getNode() {
            return node;
        }

        @Override
        public void drawEntry(int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTicks) {
            GuiListExtendedView list = (GuiListExtendedView) this.getList();
            int width = entryWidth - list.getOffset().getHorizontal() - 9;
            if (this.getX() != node.getX() || this.getY() != node.getY())
                node.setPosition(this.getX(), this.getY());
            if (width != node.getWidth() || entryHeight != node.getHeight())
                node.setPrefSize(width, entryHeight);
            node.render(mouseX, mouseY, partialTicks);
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
            if (node.getView().isVisible() && node.getView().mouseClicked(event.getMouseX(), event.getMouseY(), event.getButton())) {
                ScreenEventListener.super.onMouseClicked(event);
                node.onMouseClicked(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onMouseReleased(GuiScreenEvent.MouseReleasedEvent event) {
            if (node.getView().isVisible() && node.getView().mouseReleased(event.getMouseX(), event.getMouseY(), event.getButton())) {
                ScreenEventListener.super.onMouseReleased(event);
                node.onMouseReleased(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onMouseDragged(GuiScreenEvent.MouseDragEvent event) {
            if (node.getView().isVisible() && node.getView().mouseDragged(event.getMouseX(), event.getMouseY(), event.getMouseButton(), event.getDragX(), event.getDragY())) {
                ScreenEventListener.super.onMouseDragged(event);
                node.onMouseDragged(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onMouseScrolled(GuiScreenEvent.MouseScrollEvent event) {
            if (node.getView().isVisible() && node.getView().mouseScrolled(event.getScrollDelta())) {
                ScreenEventListener.super.onMouseScrolled(event);
                node.onMouseScrolled(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent event) {
            if (node.getView().isVisible() && node.getView().keyPressed(event.getKeyCode(), event.getScanCode(), event.getModifiers())) {
                ScreenEventListener.super.onKeyPressed(event);
                node.onKeyPressed(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onKeyReleased(GuiScreenEvent.KeyboardKeyReleasedEvent event) {
            if (node.getView().isVisible() && node.getView().keyReleased(event.getKeyCode(), event.getScanCode(), event.getModifiers())) {
                ScreenEventListener.super.onKeyReleased(event);
                node.onKeyReleased(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onCharTyped(GuiScreenEvent.KeyboardCharTypedEvent event) {
            if (node.getView().isVisible() && node.getView().charTyped(event.getCodePoint(), event.getModifiers())) {
                ScreenEventListener.super.onCharTyped(event);
                node.onCharTyped(event);
                return true;
            }
            return false;
        }

        @Override
        public void updateChildrenPos() {
            Minecraft.getInstance().mouseHelper.ungrabMouse();
            this.getNode().computeSize();
            this.getNode().updateSize();
            if (this.getNode() instanceof Parent) {
                ((Parent) this.getNode()).updateChildrenPos();
            }
        }

        @Override
        public List<? extends ScreenEventListener> getChildren() {
            return Collections.singletonList(node);
        }
    }

    public static class GuiListExtendedView<E extends GuiListExtended.IGuiListEntry<E>> extends GuiListExtended<E> implements Node.GuiView {

        private Insets offset;

        public GuiListExtendedView(int slotHeight) {
            super(Minecraft.getInstance(), 0, 0, 0, 0, slotHeight);
            offset = Insets.NONE;
        }

        public void setSlotHeight(int slotHeight) {
            ObfuscationReflectionHelper.setPrivateValue(GuiSlot.class, this, slotHeight, "field_148149_f");
        }

        public Insets getOffset() {
            return offset;
        }

        public void setOffset(Insets offset) {
            int x = this.getX();
            int y = this.getY();
            this.offset = offset;
            left = x + offset.getLeft();
            right = x + width - offset.getRight();
            top = y + offset.getTop();
            bottom = y + height - offset.getBottom();
        }

        @Override
        public int getX() {
            return left - offset.getLeft();
        }

        @Override
        public void setX(int x) {
            left = x + offset.getLeft();
            right = x + width - offset.getRight();
        }

        @Override
        public int getY() {
            return top - offset.getTop();
        }

        @Override
        public void setY(int y) {
            top = y + offset.getTop();
            bottom = y + height - offset.getBottom();
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public void setWidth(int width) {
            this.width = width;
            right = left + width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public void setHeight(int height) {
            this.height = height;
            bottom = top + height;
        }

        @Override
        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        @Override
        public int getListWidth() {
            return width;
        }

        @Override
        protected int getScrollBarX() {
            return right - 7;
        }

        @Override
        public void setHasListHeader(boolean hasListHeaderIn, int headerPaddingIn) {
            super.setHasListHeader(hasListHeaderIn, headerPaddingIn);
        }

        @Override
        public void render(int mouseX, int mouseY, float partialTicks) {
            super.drawScreen(mouseX, mouseY, partialTicks);
        }

        @Override
        protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn) {
            drawCenteredString(mc.fontRenderer, "Header", insideLeft + width / 2 - offset.getRight(), insideTop, 0xffffff);
        }

        @Override
        public boolean keyReleased(int p_keyReleased_1_, int p_keyReleased_2_, int p_keyReleased_3_) {
            return super.keyReleased(p_keyReleased_1_, p_keyReleased_2_, p_keyReleased_3_);
        }

    }
}
