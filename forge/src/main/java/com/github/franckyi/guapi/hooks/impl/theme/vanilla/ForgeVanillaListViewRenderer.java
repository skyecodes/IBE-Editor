package com.github.franckyi.guapi.hooks.impl.theme.vanilla;

import com.github.franckyi.guapi.event.*;
import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaDelegatedRenderer;
import com.github.franckyi.guapi.node.ListView;
import com.github.franckyi.guapi.node.Node;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.list.AbstractList;

public class ForgeVanillaListViewRenderer<E> extends AbstractList<ForgeVanillaListViewRenderer.NodeEntry> implements VanillaDelegatedRenderer<MatrixStack> {
    private final ListView<E> node;
    private boolean shouldRefreshSize = true;
    private boolean shouldRefreshList = true;

    public ForgeVanillaListViewRenderer(ListView<E> node) {
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
        node.getItems().addListener(this::shouldRefreshList);
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
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (shouldRefreshSize) {
            refreshSize();
            shouldRefreshSize = false;
        }
        if (shouldRefreshList) {
            refreshList();
            shouldRefreshList = false;
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void shouldRefreshSize() {
        this.shouldRefreshSize = true;
    }

    private void shouldRefreshList() {
        this.shouldRefreshList = true;
    }

    private void refreshSize() {
        width = node.getFullWidth();
        height = node.getFullHeight();
        x0 = node.getLeft();
        x1 = node.getRight();
        y0 = node.getTop();
        y1 = node.getBottom();
    }

    private void refreshList() {
        getEventListeners().clear();
        node.getItems().stream()
                .map(node.getRenderer()::getView)
                .map(n -> new NodeEntry(node, n))
                .forEach(this::addEntry);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return false;
    }

    @Override
    public void tick() {
        for (NodeEntry child : getEventListeners()) {
            child.node.tick();
        }
    }

    @Override
    public void mouseClicked(MouseButtonEvent event) {
        VanillaDelegatedRenderer.super.mouseClicked(event);
        handleMouseEvent(ScreenEventType.MOUSE_CLICKED, event);
    }

    @Override
    public void mouseReleased(MouseButtonEvent event) {
        VanillaDelegatedRenderer.super.mouseReleased(event);
        handleMouseEvent(ScreenEventType.MOUSE_RELEASED, event);
    }

    @Override
    public void mouseDragged(MouseDragEvent event) {
        VanillaDelegatedRenderer.super.mouseDragged(event);
        handleMouseEvent(ScreenEventType.MOUSE_DRAGGED, event);
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        VanillaDelegatedRenderer.super.mouseScrolled(event);
        handleMouseEvent(ScreenEventType.MOUSE_SCOLLED, event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        VanillaDelegatedRenderer.super.mouseMoved(event);
        handleMouseEvent(ScreenEventType.MOUSE_MOVED, event);
    }

    private <E extends MouseEvent> boolean handleMouseEvent(ScreenEventType<E> type, E event) {
        for (NodeEntry child : getEventListeners()) {
            child.node.handleEvent(type, event);
            if (event.getTarget() != null) break;
        }
        return event.getTarget() != null;
    }

    protected static class NodeEntry extends AbstractList.AbstractListEntry<NodeEntry> {
        private final ListView<?> parent;
        private final Node node;

        public NodeEntry(ListView<?> parent, Node node) {
            this.parent = parent;
            this.node = node;
            parent.forceParent(node);
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            parent.forceX(node, x);
            parent.forceY(node, y);
            node.setPrefWidth(entryWidth);
            node.setPrefHeight(entryHeight);
            node.render(matrices, mouseX, mouseY, tickDelta);
        }
    }
}
