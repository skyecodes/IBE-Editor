package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.gameadapter.api.Color;
import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.fabric.client.FabricRenderer;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.event.MouseDragEvent;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.event.MouseScrollEvent;
import com.github.franckyi.guapi.api.node.ListNode;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFabricVanillaListNodeRenderer<N extends ListNode<E>, E, T extends AbstractFabricVanillaListNodeRenderer.NodeEntry<N, E, T>> extends EntryListWidget<T> implements FabricVanillaDelegateRenderer {
    protected final N node;
    protected boolean shouldRefreshSize = true;
    protected boolean shouldRefreshList = true;
    protected boolean shouldScrollTo = false;
    protected boolean shouldChangeFocus = false;

    public AbstractFabricVanillaListNodeRenderer(N node) {
        super(MinecraftClient.getInstance(), 0, 0, 0, 0, node.getItemHeight());
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

    public N getNode() {
        return node;
    }

    @Override
    public int getRowWidth() {
        return node.getWidth() - node.getPadding().getHorizontal() - 6;
    }

    @Override
    protected int getScrollbarPositionX() {
        return node.getRight() - 6;
    }

    @Override
    public int getRowLeft() {
        return node.getLeft() + node.getPadding().getLeft();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (getEntryAtPosition(mouseX, mouseY) == null && (mouseX < getScrollbarPositionX() || mouseX > node.getRight())) {
            setFocused(null);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setFocused(@Nullable Element focused) {
        super.setFocused(focused);
        if (focused == null) {
            node.setFocusedElement(null);
        } else {
            node.setFocusedElement(((NodeEntry<?, E, ?>) focused).item);
        }
    }

    @Override
    public boolean preRender(IMatrices matrices, int mouseX, int mouseY, float delta) {
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
        super.render((MatrixStack) matrices, mouseX, mouseY, delta); // doing the actual rendering here to not hide the other elements
        return res;
    }

    @Override
    public void render(IMatrices matrices, int mouseX, int mouseY, float delta) {
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
        left = node.getLeft();
        right = node.getRight();
        top = node.getTop();
        bottom = node.getBottom();
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
    public void doTick() {
        for (T child : children()) {
            child.getNode().doTick();
        }
    }

    @Override
    public void mouseClicked(MouseButtonEvent event) {
        FabricVanillaDelegateRenderer.super.mouseClicked(event);
        handleMouseEvent(ScreenEventType.MOUSE_CLICKED, event);
    }

    @Override
    public void mouseReleased(MouseButtonEvent event) {
        FabricVanillaDelegateRenderer.super.mouseReleased(event);
        handleMouseEvent(ScreenEventType.MOUSE_RELEASED, event);
    }

    @Override
    public void mouseDragged(MouseDragEvent event) {
        FabricVanillaDelegateRenderer.super.mouseDragged(event);
        handleMouseEvent(ScreenEventType.MOUSE_DRAGGED, event);
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        FabricVanillaDelegateRenderer.super.mouseScrolled(event);
        handleMouseEvent(ScreenEventType.MOUSE_SCOLLED, event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        FabricVanillaDelegateRenderer.super.mouseMoved(event);
        handleMouseEvent(ScreenEventType.MOUSE_MOVED, event);
    }

    protected <EE extends MouseEvent> void handleMouseEvent(ScreenEventType<EE> target, EE event) {
        for (T child : children()) {
            child.getNode().handleEvent(target, event);
            if (event.getTarget() != null) return;
        }
    }

    protected abstract static class NodeEntry<N extends ListNode<E>, E, T extends NodeEntry<N, E, T>> extends EntryListWidget.Entry<T> {
        private final AbstractFabricVanillaListNodeRenderer<N, E, T> list;
        private final E item;
        private Node node;

        public NodeEntry(AbstractFabricVanillaListNodeRenderer<N, E, T> list, E item) {
            this(list, item, null);
        }

        public NodeEntry(AbstractFabricVanillaListNodeRenderer<N, E, T> list, E item, Node node) {
            this.list = list;
            this.item = item;
            this.node = node;
        }

        public AbstractFabricVanillaListNodeRenderer<N, E, T> getList() {
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

        protected void renderBackground(IMatrices matrices, int x, int y, int entryWidth, int entryHeight) {
            if (getList().getFocused() == this) {
                FabricRenderer.INSTANCE.fillRectangle(matrices, x - 2, y - 2,
                        x + entryWidth + 3, y + entryHeight + 2, Color.fromRGBA(255, 255, 255, 79));
            }
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            return list.node.isChildrenFocusable();
        }
    }
}
