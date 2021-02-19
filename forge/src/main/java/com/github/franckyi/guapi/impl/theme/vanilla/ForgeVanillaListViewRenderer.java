package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;

public class ForgeVanillaListViewRenderer<E> extends AbstractForgeVanillaListNodeRenderer<ListView<E>, E> implements ForgeVanillaDelegateRenderer {
    public ForgeVanillaListViewRenderer(ListView<E> node) {
        super(node);
        node.getItems().addListener(this::shouldRefreshList);
    }

    @Override
    protected void refreshList() {
        getEventListeners().clear();
        node.getItems().stream()
                .map(node.getRenderer()::getView)
                .peek(n -> n.setParent(node))
                .map(NodeEntry::new)
                .forEach(this::addEntry);
        shouldRefreshList = false;
    }

    protected static class NodeEntry extends AbstractForgeVanillaListNodeRenderer.NodeEntry {
        private final Node node;

        public NodeEntry(Node node) {
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
