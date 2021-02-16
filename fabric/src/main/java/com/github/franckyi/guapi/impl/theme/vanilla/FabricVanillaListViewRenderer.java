package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.event.MouseDragEvent;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.event.MouseScrollEvent;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import com.github.franckyi.guapi.util.ScreenEventType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.util.math.MatrixStack;

public class FabricVanillaListViewRenderer<E> extends EntryListWidget<FabricVanillaListViewRenderer.NodeEntry> implements FabricVanillaDelegateRenderer {
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
            entry.node.postRender(matrices, mouseX, mouseY, delta);
        }
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
        shouldRefreshSize = false;
    }

    private void refreshList() {
        children().clear();
        node.getItems().stream()
                .map(node.getRenderer()::getView)
                .peek(n -> n.setParent(node))
                .map(n -> new NodeEntry(this, n))
                .forEach(this::addEntry);
        shouldRefreshList = false;
    }

    @Override
    public void tick() {
        for (NodeEntry child : children()) {
            child.node.tick();
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

    private <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {
        for (NodeEntry child : children()) {
            child.node.handleEvent(type, event);
            if (event.getTarget() != null) return;
        }
    }

    protected static class NodeEntry extends EntryListWidget.Entry<NodeEntry> {
        private final EntryListWidget<NodeEntry> list;
        private final Node node;

        public NodeEntry(EntryListWidget<NodeEntry> list, Node node) {
            this.list = list;
            this.node = node;
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            node.setX(x);
            node.setY(y);
            node.setParentPrefWidth(list.getMaxScroll() == 0 ? entryWidth + 6 : entryWidth);
            node.setParentPrefHeight(entryHeight);
            while (node.preRender(matrices, mouseX, mouseY, tickDelta)) ;
            node.render(matrices, mouseX, mouseY, tickDelta);
        }
    }
}
