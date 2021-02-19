package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.event.MouseDragEvent;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.event.MouseScrollEvent;
import com.github.franckyi.guapi.api.node.ListNode;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import com.github.franckyi.guapi.util.ScreenEventType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.util.math.MatrixStack;

public abstract class AbstractFabricVanillaListNodeRenderer<N extends ListNode<E>, E> extends EntryListWidget<AbstractFabricVanillaListNodeRenderer.NodeEntry> implements FabricVanillaDelegateRenderer {
    protected final N node;
    protected boolean shouldRefreshSize = true;
    protected boolean shouldRefreshList = true;

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
    public boolean preRender(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        boolean res = false;
        if (shouldRefreshSize) {
            refreshSize();
            res = true;
        }
        if (shouldRefreshList) {
            refreshList();
            res = true;
        }
        super.render(matrices, mouseX, mouseY, delta); // doing the actual rendering here to not hide the other elements
        return res;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        for (NodeEntry entry : children()) {
            entry.getNode().postRender(matrices, mouseX, mouseY, delta);
        }
    }

    protected void shouldRefreshSize() {
        this.shouldRefreshSize = true;
    }

    protected void shouldRefreshList() {
        this.shouldRefreshList = true;
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

    protected abstract void refreshList();

    @Override
    public void tick() {
        for (NodeEntry child : children()) {
            child.getNode().tick();
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

    protected <EE extends MouseEvent> void handleMouseEvent(ScreenEventType<EE> type, EE event) {
        for (NodeEntry child : children()) {
            child.getNode().handleEvent(type, event);
            if (event.getTarget() != null) return;
        }
    }

    protected abstract static class NodeEntry extends EntryListWidget.Entry<NodeEntry> {
        protected abstract Node getNode();
    }
}
