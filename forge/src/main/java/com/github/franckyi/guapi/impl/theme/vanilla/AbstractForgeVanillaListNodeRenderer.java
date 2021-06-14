package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.event.MouseDragEvent;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.event.MouseScrollEvent;
import com.github.franckyi.guapi.api.node.ListNode;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.guapi.util.ScreenEventType;
import com.github.franckyi.minecraft.api.client.render.Matrices;
import com.github.franckyi.minecraft.impl.client.render.ForgeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.widget.list.AbstractList;

import javax.annotation.Nullable;

public abstract class AbstractForgeVanillaListNodeRenderer<N extends ListNode<E>, E, T extends AbstractForgeVanillaListNodeRenderer.NodeEntry<N, E, T>> extends AbstractList<T> implements ForgeVanillaDelegateRenderer {
    protected final N node;
    protected boolean shouldRefreshSize = true;
    protected boolean shouldRefreshList = true;
    protected boolean shouldScrollTo = false;
    protected boolean shouldChangeFocus = false;

    public AbstractForgeVanillaListNodeRenderer(N node) {
        super(Minecraft.getInstance(), 0, 0, 0, 0, node.getItemHeight());
        this.node = node;
        Runnable rs = this::shouldRefreshSize;
        node.xProperty().addListener(rs);
        node.yProperty().addListener(rs);
        node.baseXProperty().addListener(rs);
        node.baseYProperty().addListener(rs);
        node.widthProperty().addListener(rs);
        node.heightProperty().addListener(rs);
        node.fullWidthProperty().addListener(rs);
        node.fullHeightProperty().addListener(rs);
        node.rootProperty().addListener(this::shouldRefreshList);
        node.scrollToProperty().addListener(this::shouldScrollTo);
        node.focusedElementProperty().addListener(this::shouldChangeFocus);
    }

    @Override
    public int getRowWidth() {
        return node.getWidth() - node.getPadding().getHorizontal() - 6;
    }

    @Override
    protected int getScrollbarPosition() {
        return node.getRight() - 6;
    }

    @Override
    public int getRowLeft() {
        return node.getLeft() + node.getPadding().getLeft();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (getEntryAtPosition(mouseX, mouseY) == null) {
            setFocused(null);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setFocused(@Nullable IGuiEventListener listener) {
        super.setFocused(listener);
        if (listener == null) {
            node.setFocusedElement(null);
        } else {
            node.setFocusedElement(((NodeEntry<?, E, ?>) listener).item);
        }
    }

    @Override
    public boolean preRender(Matrices matrices, int mouseX, int mouseY, float delta) {
        boolean res = false;
        if (shouldRefreshSize) {
            refreshSize();
            res = true;
        }
        if (shouldRefreshList) {
            refreshList();
            res = true;
        }
        if (shouldScrollTo) {
            scrollTo();
            res = true;
        }
        if (shouldChangeFocus) {
            changeFocus();
            res = true;
        }
        super.render(matrices.getMatrixStack(), mouseX, mouseY, delta); // doing the actual rendering here to not hide the other elements
        return res;
    }

    @Override
    public void render(Matrices matrices, int mouseX, int mouseY, float delta) {
        for (T entry : children()) {
            entry.getNode().postRender(matrices, mouseX, mouseY, delta);
        }
    }

    protected void shouldRefreshSize() {
        shouldRefreshSize = true;
    }

    protected void shouldRefreshList() {
        shouldRefreshList = true;
    }

    protected void shouldScrollTo() {
        shouldScrollTo = true;
    }

    protected void shouldChangeFocus() {
        shouldChangeFocus = true;
    }

    protected void refreshSize() {
        width = node.getFullWidth();
        height = node.getFullHeight();
        x0 = node.getLeft();
        x1 = node.getRight();
        y0 = node.getTop();
        y1 = node.getBottom();
        shouldRefreshSize = false;
    }

    protected void refreshList() {
        clearEntries();
        createList();
        setScrollAmount(getScrollAmount());
        shouldRefreshList = false;
    }

    protected abstract void createList();

    protected void scrollTo() {
        for (T e : children()) {
            if (e.getItem() == node.getScrollTo()) {
                centerScrollOn(e);
                break;
            }
        }
        shouldScrollTo = false;
    }

    protected void changeFocus() {
        for (T e : children()) {
            if (e.getItem() == node.getFocusedElement()) {
                super.setFocused(e);
                break;
            }
        }
        shouldChangeFocus = false;
    }

    @Override
    public void tick() {
        for (T child : children()) {
            child.getNode().tick();
        }
    }

    @Override
    public void mouseClicked(MouseButtonEvent event) {
        ForgeVanillaDelegateRenderer.super.mouseClicked(event);
        handleMouseEvent(ScreenEventType.MOUSE_CLICKED, event);
    }

    @Override
    public void mouseReleased(MouseButtonEvent event) {
        ForgeVanillaDelegateRenderer.super.mouseReleased(event);
        handleMouseEvent(ScreenEventType.MOUSE_RELEASED, event);
    }

    @Override
    public void mouseDragged(MouseDragEvent event) {
        ForgeVanillaDelegateRenderer.super.mouseDragged(event);
        handleMouseEvent(ScreenEventType.MOUSE_DRAGGED, event);
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        ForgeVanillaDelegateRenderer.super.mouseScrolled(event);
        handleMouseEvent(ScreenEventType.MOUSE_SCOLLED, event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        ForgeVanillaDelegateRenderer.super.mouseMoved(event);
        handleMouseEvent(ScreenEventType.MOUSE_MOVED, event);
    }

    protected <EE extends MouseEvent> void handleMouseEvent(ScreenEventType<EE> type, EE event) {
        for (T child : children()) {
            child.getNode().handleEvent(type, event);
            if (event.getTarget() != null) return;
        }
    }

    protected abstract static class NodeEntry<N extends ListNode<E>, E, T extends NodeEntry<N, E, T>> extends AbstractList.AbstractListEntry<T> {
        private final AbstractForgeVanillaListNodeRenderer<N, E, T> list;
        private final E item;
        private Node node;

        public NodeEntry(AbstractForgeVanillaListNodeRenderer<N, E, T> list, E item) {
            this(list, item, null);
        }

        public NodeEntry(AbstractForgeVanillaListNodeRenderer<N, E, T> list, E item, Node node) {
            this.list = list;
            this.item = item;
            this.node = node;
        }

        public AbstractForgeVanillaListNodeRenderer<N, E, T> getList() {
            return list;
        }

        public E getItem() {
            return item;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        protected void renderBackground(Matrices matrices, int x, int y, int entryWidth, int entryHeight) {
            if (getList().getFocused() == this) {
                ForgeRenderer.INSTANCE.fillRectangle(matrices, x - 2, y - 2,
                        x + entryWidth + 3, y + entryHeight + 2, Color.rgba(255, 255, 255, 79));
            }
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            return list.node.isChildrenFocusable();
        }
    }
}
