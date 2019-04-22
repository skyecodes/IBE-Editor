package com.github.franckyi.ibeeditor.client.clipboard;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.*;
import com.github.franckyi.ibeeditor.client.ClientUtils;
import com.github.franckyi.ibeeditor.client.IResizable;
import com.github.franckyi.ibeeditor.client.clipboard.logic.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.clipboard.logic.IBEClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.logic.ItemClipboardEntry;
import com.github.franckyi.ibeeditor.client.editor.item.ItemEditor;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class ViewClipboard extends AbstractClipboard {

    protected final HBox filterBox;
    protected final Label filterLabel;
    protected final EnumButton<Filter> filterButton;
    protected final Button closeButton;
    protected final HBox blankBox;

    public ViewClipboard() {
        super("IBE Clipboard");
        footer.getChildren().add(filterBox = new HBox(5));
        filterBox.getChildren().add(filterLabel = new Label("Filter :"));
        filterLabel.setPrefHeight(20);
        filterBox.getChildren().add(filterButton = new EnumButton<>(Filter.values()));
        filterButton.getOnValueChangedListeners().add((oldVal, newVal) -> this.setFilter(newVal));
        filterButton.setValue(Filter.ITEM);
        footer.getChildren().add(closeButton = new Button(ChatFormatting.GREEN + "Close"));
        closeButton.getOnMouseClickedListeners().add(event -> this.close());
        closeButton.setPrefWidth(80);
        footer.getChildren().add(blankBox = new HBox());
        this.show();
    }

    @Override
    protected void scaleChildrenSize() {
        super.scaleChildrenSize();
        blankBox.setPrefWidth(filterBox.getWidth());
    }

    @Override
    protected void newItemEntry(ItemClipboardEntry item) {
        body.getChildren().add(new ItemView(item));
    }

    @Override
    protected void newEntityEntry(EntityClipboardEntry entity) {

    }

    private class ItemView extends ListExtended.NodeEntry<HBox> implements IResizable {

        private final Label nameLabel;
        private final Button copyButton;
        private final Button editButton;
        private final TexturedButton removeButton;

        public ItemView(ItemClipboardEntry item) {
            super(new HBox(10));
            this.getNode().setAlignment(Pos.LEFT);
            ItemStack itemStack = item.getItemStack();
            List<Node> children = this.getNode().getChildren();
            children.add(new TexturedButton(itemStack));
            children.add(nameLabel = new Label(itemStack.getDisplayName().getFormattedText()));
            children.add(copyButton = new Button("Copy /give command"));
            copyButton.setPrefWidth(130);
            copyButton.getOnMouseClickedListeners().add(event -> ClientUtils.copyGiveCommand(itemStack));
            children.add(editButton = new Button("Edit"));
            editButton.getOnMouseClickedListeners().add(event -> new ItemEditor(itemStack, null, stack -> {
                List<ItemClipboardEntry> items = IBEClipboard.getInstance().getItems();
                items.set(items.indexOf(item), new ItemClipboardEntry(stack));
                IBEClipboard.getInstance().save();
            }));
            children.add(removeButton =
                    new TexturedButton("delete.png", TextFormatting.RED + "Remove"));
            removeButton.getOnMouseClickedListeners().add(event -> {
                IBEClipboard.getInstance().removeItem(itemStack);
                setFilter(filter);
            });
        }

        @Override
        public void updateSize(int listWidth) {
            nameLabel.setPrefWidth(listWidth - 270);
        }
    }
}
