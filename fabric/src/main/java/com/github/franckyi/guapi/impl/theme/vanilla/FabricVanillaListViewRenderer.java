package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import net.minecraft.client.util.math.MatrixStack;

public class FabricVanillaListViewRenderer<E> extends AbstractFabricVanillaListNodeRenderer<ListView<E>, E> {
    public FabricVanillaListViewRenderer(ListView<E> node) {
        super(node);
    }

    @Override
    protected void refreshList() {
        children().clear();
        node.getItems().stream()
                .map(node.getRenderer()::getView)
                .peek(n -> n.setParent(node))
                .map(n -> new NodeEntry(this, n))
                .forEach(this::addEntry);
        shouldRefreshList = false;
    }

    protected static class NodeEntry extends AbstractFabricVanillaListNodeRenderer.NodeEntry {
        private final FabricVanillaListViewRenderer<?> list;
        private final Node node;

        public NodeEntry(FabricVanillaListViewRenderer<?> list, Node node) {
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

        @Override
        protected Node getNode() {
            return node;
        }
    }
}
