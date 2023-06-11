package com.github.franckyi.guapi.base.theme.vanilla.delegate;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;

public class VanillaListViewSkinDelegate<E> extends AbstractVanillaListNodeSkinDelegate<ListView<E>, E, VanillaListViewSkinDelegate.NodeEntry<E>> {
    public VanillaListViewSkinDelegate(ListView<E> node) {
        super(node);
        node.getItems().addListener(this::shouldRefreshList);
    }

    @Override
    protected void createList() {
        for (E item : node.getItems()) {
            Node view = node.getRenderer().getView(item);
            view.setParent(node);
            NodeEntry<E> entry = new NodeEntry<>(this, item, view);
            addEntry(entry);
            if (item == node.getFocusedElement()) {
                setFocused(entry);
            }
        }
    }

    protected static class NodeEntry<E> extends AbstractVanillaListNodeSkinDelegate.NodeEntry<ListView<E>, E, NodeEntry<E>> {
        public NodeEntry(VanillaListViewSkinDelegate<E> list, E item, Node node) {
            super(list, item, node);
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            entryWidth = getList().getMaxScroll() == 0 ? entryWidth + 6 : entryWidth;
            getNode().setX(x);
            getNode().setY(y);
            getNode().setParentPrefWidth(entryWidth);
            getNode().setParentPrefHeight(entryHeight);
            renderBackground(guiGraphics, x, y, entryWidth, entryHeight);
            while (getNode().preRender(guiGraphics, mouseX, mouseY, tickDelta)) ;
            getNode().render(guiGraphics, mouseX, mouseY, tickDelta);
        }
    }
}
