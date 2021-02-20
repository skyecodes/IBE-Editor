package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import net.minecraft.client.util.math.MatrixStack;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class FabricVanillaTreeViewRenderer<E extends TreeView.TreeItem<E>> extends AbstractFabricVanillaListNodeRenderer<TreeView<E>, E> implements FabricVanillaDelegateRenderer {
    public FabricVanillaTreeViewRenderer(TreeView<E> node) {
        super(node);
        node.rootProperty().addListener(this::shouldRefreshList);
        node.showRootProperty().addListener(this::shouldRefreshList);
    }

    @Override
    protected void refreshList() {
        children().clear();
        if (node.rootItemProperty().hasValue()) {
            if (node.isShowRoot()) {
                addChild(node.getRoot(), 0);
            } else {
                for (E child : node.getRoot().getChildren()) {
                    addChild(child, 0);
                }
            }
        }
        shouldRefreshList = false;
    }

    private void addChild(E item, int increment) {
        children().add(new NodeEntry<>(this, item, node.getRenderer().getView(item), increment));
        increment++;
        if (item.isExpanded()) {
            for (E child : item.getChildren()) {
                addChild(child, increment);
            }
        }
    }

    protected static class NodeEntry<E extends TreeView.TreeItem<E>> extends AbstractFabricVanillaListNodeRenderer.NodeEntry {
        private final FabricVanillaTreeViewRenderer<E> list;
        private final HBox node;
        private final int increment;

        public NodeEntry(FabricVanillaTreeViewRenderer<E> list, E item, Node node, int increment) {
            this.list = list;
            this.node = hBox(root -> {
                if (item.getChildren().isEmpty()) {
                    root.add(hBox().prefWidth(20));
                } else {
                    root.add(button(item.isExpanded() ? "v" : ">").prefWidth(20).action(() -> {
                        if (!item.isExpanded()) {
                            chainExpand(item);
                        } else {
                            item.setExpanded(false);
                        }
                        list.shouldRefreshList();
                    }));
                }
                root.add(node, 1).align(CENTER).spacing(5).setParent(list.node);
            });
            this.increment = increment;
        }

        private void chainExpand(E item) {
            item.setExpanded(true);
            if (item.isExpanded() && item.getChildren().size() == 1) {
                chainExpand(item.getChildren().get(0));
            }
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            int incr = increment * list.node.getChildrenIncrement();
            entryWidth = entryWidth - incr;
            node.setX(x + incr);
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
