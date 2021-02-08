package com.github.franckyi.guapi.hooks.impl.theme.vanilla;

import com.github.franckyi.guapi.event.*;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaDelegatedRenderer;
import com.github.franckyi.guapi.node.ListView;
import com.github.franckyi.guapi.node.Node;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.util.math.MatrixStack;

public class FabricVanillaListViewRenderer<E> extends EntryListWidget<FabricVanillaListViewRenderer.NodeEntry> implements VanillaDelegatedRenderer<MatrixStack> {
    private final ListView<E> node;
    private boolean shouldRefreshSize = true;
    private boolean shouldRefreshList = true;

    public FabricVanillaListViewRenderer(ListView<E> node) {
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
        node.getItems().addListener(this::shouldRefreshList);
        //method_31322(false); // draw darker background
        //method_31323(false); // draw shadows on top & bottom
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
        left = node.getLeft();
        right = node.getRight();
        top = node.getTop();
        bottom = node.getBottom();
    }

    private void refreshList() {
        children().clear();
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
        for (NodeEntry child : children()) {
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
        for (NodeEntry child : children()) {
            child.node.handleEvent(type, event);
            if (event.getTarget() != null) break;
        }
        return event.getTarget() != null;
    }

    protected static class NodeEntry extends EntryListWidget.Entry<NodeEntry> {
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
            node.render(RenderContext.of(matrices, mouseX, mouseY, tickDelta));
        }
    }
}
