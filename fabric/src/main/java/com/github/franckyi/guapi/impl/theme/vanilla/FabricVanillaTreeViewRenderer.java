package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.node.TreeView;
import net.minecraft.client.util.math.MatrixStack;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class FabricVanillaTreeViewRenderer<E extends TreeView.TreeItem<E>> extends AbstractFabricVanillaListNodeRenderer<TreeView<E>, E, FabricVanillaTreeViewRenderer.NodeEntry<E>> {
    public FabricVanillaTreeViewRenderer(TreeView<E> node) {
        super(node);
        node.rootItemProperty().addListener(this::shouldRefreshList);
        node.showRootProperty().addListener(this::shouldRefreshList);
        node.rootItemProperty().bindMapToBoolean(TreeView.TreeItem::childrenChangedProperty, false).addListener(newVal -> {
            if (newVal) {
                shouldRefreshList();
                node.getRoot().setChildrenChanged(false);
            }
        });
    }

    @Override
    protected void refreshList() {
        clearEntries();
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
        NodeEntry<E> entry = new NodeEntry<>(this, item, node.getRenderer().getView(item), increment);
        children().add(entry);
        if (item == node.getFocusedElement()) {
            setFocused(entry);
        }
        increment++;
        if (item.isExpanded()) {
            for (E child : item.getChildren()) {
                addChild(child, increment);
            }
        }
    }

    protected static class NodeEntry<E extends TreeView.TreeItem<E>> extends AbstractFabricVanillaListNodeRenderer.NodeEntry<TreeView<E>, E, NodeEntry<E>> {
        private TexturedButton button;
        private final int increment;

        public NodeEntry(FabricVanillaTreeViewRenderer<E> list, E item, Node node, int increment) {
            super(list, item);
            setNode(hBox(root -> {
                if (item.getChildren().isEmpty()) {
                    root.add(hBox().prefSize(16, 16));
                } else {
                    root.add(button = texturedButton("ibeeditor:textures/gui/tree_view_widgets.png", 32, 32, false).prefSize(16, 16).action(() -> {
                        if (!item.isExpanded()) {
                            chainExpand(item);
                        } else {
                            item.setExpanded(false);
                        }
                        list.shouldRefreshList();
                    }));
                    if (item.isExpanded()) {
                        button.setImageX(16);
                    }
                    item.expandedProperty().addListener(newVal -> button.setImageX(newVal ? 16 : 0));
                }
                root.add(node).align(CENTER_LEFT).spacing(5).setParent(list.node);
            }));
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
            int incr = increment * getList().node.getChildrenIncrement();
            entryWidth = getList().getMaxScroll() == 0 ? entryWidth + 6 : entryWidth;
            getNode().setX(x + incr);
            getNode().setY(y);
            getNode().setParentPrefWidth(entryWidth - incr);
            getNode().setParentPrefHeight(entryHeight);
            if (button != null) {
                button.setImageY(button.inBounds(mouseX, mouseY) ? 16 : 0);
            }
            renderBackground(matrices, x, y, entryWidth, entryHeight);
            while (getNode().preRender(matrices, mouseX, mouseY, tickDelta)) ;
            getNode().render(matrices, mouseX, mouseY, tickDelta);
        }
    }
}
