package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.impl.client.ForgeShapeRenderer;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;

import javax.annotation.Nullable;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class ForgeVanillaTreeViewRenderer<E extends TreeView.TreeItem<E>> extends AbstractForgeVanillaListNodeRenderer<TreeView<E>, E> implements ForgeVanillaDelegateRenderer {
    public ForgeVanillaTreeViewRenderer(TreeView<E> node) {
        super(node);
        node.rootProperty().addListener(this::shouldRefreshList);
        node.showRootProperty().addListener(this::shouldRefreshList);
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
        getEventListeners().add(entry);
        if (item == node.getFocusedElement()) {
            setListener(entry);
        }
        increment++;
        if (item.isExpanded()) {
            for (E child : item.getChildren()) {
                addChild(child, increment);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.getEntryAtPosition(mouseX, mouseY) == null) {
            setListener(null);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setListener(@Nullable IGuiEventListener listener) {
        super.setListener(listener);
        if (listener == null) {
            node.setFocusedElement(null);
        } else {
            node.setFocusedElement(((NodeEntry<E>) listener).item);
        }
    }

    protected static class NodeEntry<E extends TreeView.TreeItem<E>> extends AbstractForgeVanillaListNodeRenderer.NodeEntry {
        private final ForgeVanillaTreeViewRenderer<E> list;
        private final E item;
        private final HBox node;
        private TexturedButton button;
        private final int increment;

        public NodeEntry(ForgeVanillaTreeViewRenderer<E> list, E item, Node node, int increment) {
            this.list = list;
            this.item = item;
            this.node = hBox(root -> {
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
            });
            this.increment = increment;
        }

        private void chainExpand(E item) {
            item.setExpanded(true);
            if (item.getChildren().size() == 1) {
                chainExpand(item.getChildren().get(0));
            }
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            int incr = increment * list.node.getChildrenIncrement();
            entryWidth = list.getMaxScroll() == 0 ? entryWidth + 6 : entryWidth;
            node.setX(x + incr);
            node.setY(y);
            node.setParentPrefWidth(entryWidth - incr);
            node.setParentPrefHeight(entryHeight);
            if (button != null) {
                button.setImageY(button.inBounds(mouseX, mouseY) ? 16 : 0);
            }
            if (list.getListener() == this) {
                ForgeShapeRenderer.INSTANCE.fillRectangle(matrices, x - 2, y - 2,
                        x + entryWidth + 3, y + entryHeight + 2, 0x4fffffff);
            }
            while (node.preRender(matrices, mouseX, mouseY, tickDelta)) ;
            node.render(matrices, mouseX, mouseY, tickDelta);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            return list.node.isChildrenFocusable();
        }

        @Override
        protected Node getNode() {
            return node;
        }
    }
}
