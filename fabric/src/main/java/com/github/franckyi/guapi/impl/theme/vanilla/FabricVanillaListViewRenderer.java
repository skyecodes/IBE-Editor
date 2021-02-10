package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.event.MouseDragEvent;
import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.event.MouseScrollEvent;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.vanilla.VanillaDelegatedRenderer;
import com.github.franckyi.guapi.util.ScreenEventType;
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
    public boolean preRender() {
        boolean res = checkRender();
        for (NodeEntry entry : children()) {
            res |= entry.node.preRender();
        }
        return res;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        checkRender();
        super.render(matrices, mouseX, mouseY, delta);
    }

    protected boolean checkRender() {
        boolean res = false;
        if (shouldRefreshSize) {
            refreshSize();
            res = true;
        }
        if (shouldRefreshList) {
            refreshList();
            res = true;
        }
        return res;
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
                .map(n -> new NodeEntry(node, n))
                .forEach(this::addEntry);
        shouldRefreshList = false;
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

    private <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {
        for (NodeEntry child : children()) {
            child.node.handleEvent(type, event);
            if (event.getTarget() != null) return;
        }
    }

    protected static class NodeEntry extends EntryListWidget.Entry<NodeEntry> {
        private final ListView<?> parent;
        private final Node node;

        public NodeEntry(ListView<?> parent, Node node) {
            this.parent = parent;
            this.node = node;
            node.setParent(parent);
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            node.setX(x);
            node.setY(y);
            node.setPrefWidth(entryWidth);
            node.setPrefHeight(entryHeight);
            node.render(matrices, mouseX, mouseY, tickDelta);
        }
    }
}
