package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.IParent;
import com.github.franckyi.guapi.IScreenEventListener;
import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.event.IEventListener;
import com.github.franckyi.guapi.gui.IGuiView;
import com.github.franckyi.guapi.math.Insets;
import net.minecraft.client.gui.widget.list.AbstractList;
import net.minecraft.client.gui.widget.list.ExtendedList;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListExtended<E extends ExtendedList.AbstractListEntry & IScreenEventListener> extends Node<ListExtended.GuiListExtendedView> implements IParent {

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

    @Override
    public void updateChildrenPos() {
        this.computeSize();
        this.updateSize();
    }

    @SuppressWarnings("unchecked")
    public List<E> getChildren() {
        return this.getView().children();
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(200);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(200);
    }

    public static class GuiListExtendedView<E extends ExtendedList.AbstractListEntry<E>> extends ExtendedList<E> implements IGuiView {

        private Insets offset;
        private boolean visible;
        private int realHeight;

        public GuiListExtendedView(int slotHeight) {
            super(IScreenEventListener.mc, 0, 0, 0, 0, slotHeight);
            offset = Insets.NONE;
            visible = true;
        }

        public void setSlotHeight(int slotHeight) {
            ObfuscationReflectionHelper.setPrivateValue(AbstractList.class, this, slotHeight, "itemHeight");
        }

        public Insets getOffset() {
            return offset;
        }

        public void setOffset(Insets offset) {
            int x = this.getViewX();
            int y = this.getViewY();
            this.offset = offset;
            x0 = x + offset.getLeft(); // left
            x1 = x + width - offset.getRight(); // right
            y0 = y + offset.getTop(); // top
            y1 = y + realHeight - offset.getBottom(); // bottom
        }

        @Override
        public int getViewX() {
            return x0 - offset.getLeft();
        }

        @Override
        public void setViewX(int x) {
            x0 = x + offset.getLeft();
            x1 = x + width - offset.getRight();
        }

        @Override
        public int getViewY() {
            return y0 - offset.getTop();
        }

        @Override
        public void setViewY(int y) {
            y0 = y + offset.getTop();
            y1 = y + realHeight - offset.getBottom();
        }

        @Override
        public int getViewWidth() {
            return width;
        }

        @Override
        public void setViewWidth(int width) {
            this.width = width;
            x1 = x0 + width;
        }

        @Override
        public int getViewHeight() {
            return realHeight;
        }

        @Override
        public void setViewHeight(int height) {
            this.realHeight = height;
            y1 = y0 + height;
        }

        @Override
        public boolean isViewVisible() {
            return this.visible;
        }

        @Override
        public void setViewVisible(boolean visible) {
            this.visible = visible;
        }

        @Override
        protected int getScrollbarPosition() {
            return x1 - 7;
        }

        @Override
        public int getRowWidth() {
            return this.getWidth();
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            this.render(mouseX, mouseY, partialTicks);
        }

        @Override
        public boolean keyReleased(int p_keyReleased_1_, int p_keyReleased_2_, int p_keyReleased_3_) {
            return super.keyReleased(p_keyReleased_1_, p_keyReleased_2_, p_keyReleased_3_);
        }

        @Override
        public boolean charTyped(char charTyped, int modifiers) {
            return IGuiView.super.charTyped(charTyped, modifiers);
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class NodeEntry<T extends Node<?>> extends ExtendedList.AbstractListEntry<NodeEntry<T>> implements IScreenEventListener, IParent {

        private final Set<IEventListener<GuiScreenEvent.MouseClickedEvent>> onMouseClickedListeners;
        private final Set<IEventListener<GuiScreenEvent.MouseReleasedEvent>> onMouseReleasedListeners;
        private final Set<IEventListener<GuiScreenEvent.MouseDragEvent>> onMouseDraggedListeners;
        private final Set<IEventListener<GuiScreenEvent.MouseScrollEvent>> onMouseScrolledListeners;
        private final Set<IEventListener<GuiScreenEvent.KeyboardKeyPressedEvent>> onKeyPressedListeners;
        private final Set<IEventListener<GuiScreenEvent.KeyboardKeyReleasedEvent>> onKeyReleasedListeners;
        private final Set<IEventListener<GuiScreenEvent.KeyboardCharTypedEvent>> onCharTypedListeners;

        private final T node;

        public NodeEntry(T node) {
            this.node = node;
            node.setParent(this);
            onMouseClickedListeners = new HashSet<>();
            onMouseReleasedListeners = new HashSet<>();
            onMouseDraggedListeners = new HashSet<>();
            onMouseScrolledListeners = new HashSet<>();
            onKeyPressedListeners = new HashSet<>();
            onKeyReleasedListeners = new HashSet<>();
            onCharTypedListeners = new HashSet<>();
        }

        public T getNode() {
            return node;
        }

        protected GuiListExtendedView<NodeEntry<T>> getList() {
            return ((GuiListExtendedView<NodeEntry<T>>) this.list);
        }

        @Override
        public void render(int index, int entryTop, int entryLeft, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTicks) {
            GuiListExtendedView<?> list = this.getList();
            int width = entryWidth - list.getOffset().getHorizontal() - 5;
            if (entryLeft != node.getX() || entryTop != node.getY())
                node.setPosition(entryLeft + 4, entryTop);
            if (width != node.getWidth() || entryHeight != node.getHeight())
                node.setPrefSize(width - 10, entryHeight);
            node.render(mouseX, mouseY, partialTicks);
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

        @Override
        public boolean onMouseClicked(GuiScreenEvent.MouseClickedEvent event) {
            if (node.getView().isViewVisible() && node.getView().mouseClicked(event.getMouseX(), event.getMouseY(), event.getButton())) {
                IScreenEventListener.super.onMouseClicked(event);
                node.onMouseClicked(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onMouseReleased(GuiScreenEvent.MouseReleasedEvent event) {
            if (node.getView().isViewVisible() && node.getView().mouseReleased(event.getMouseX(), event.getMouseY(), event.getButton())) {
                IScreenEventListener.super.onMouseReleased(event);
                node.onMouseReleased(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onMouseDragged(GuiScreenEvent.MouseDragEvent event) {
            if (node.getView().isViewVisible() && node.getView().mouseDragged(event.getMouseX(), event.getMouseY(), event.getMouseButton(), event.getDragX(), event.getDragY())) {
                IScreenEventListener.super.onMouseDragged(event);
                node.onMouseDragged(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onMouseScrolled(GuiScreenEvent.MouseScrollEvent event) {
            if (node.getView().isViewVisible() && node.getView().mouseScrolled(event.getMouseX(), event.getMouseY(), event.getScrollDelta())) {
                IScreenEventListener.super.onMouseScrolled(event);
                node.onMouseScrolled(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent event) {
            if (node.getView().isViewVisible() && node.getView().keyPressed(event.getKeyCode(), event.getScanCode(), event.getModifiers())) {
                IScreenEventListener.super.onKeyPressed(event);
                node.onKeyPressed(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onKeyReleased(GuiScreenEvent.KeyboardKeyReleasedEvent event) {
            if (node.getView().isViewVisible() && node.getView().keyReleased(event.getKeyCode(), event.getScanCode(), event.getModifiers())) {
                IScreenEventListener.super.onKeyReleased(event);
                node.onKeyReleased(event);
                return true;
            }
            return false;
        }

        @Override
        public boolean onCharTyped(GuiScreenEvent.KeyboardCharTypedEvent event) {
            if (node.getView().isViewVisible() && node.getView().charTyped(event.getCodePoint(), event.getModifiers())) {
                IScreenEventListener.super.onCharTyped(event);
                node.onCharTyped(event);
                return true;
            }
            return false;
        }

        @Override
        public void updateChildrenPos() {
            this.getNode().computeSize();
            this.getNode().updateSize();
            if (this.getNode() instanceof IParent) {
                ((IParent) this.getNode()).updateChildrenPos();
            }
        }

        @Override
        public List<? extends IScreenEventListener> getChildren() {
            return Collections.singletonList(node);
        }
    }
}
