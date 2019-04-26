package com.github.franckyi.ibeeditor.client.editor.property.custom;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.logic.ItemClipboardEntry;
import com.github.franckyi.ibeeditor.client.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.client.editor.property.LabeledCategory;
import net.minecraft.item.ItemStack;

import java.util.function.Consumer;

public class SlotProperty extends LabeledCategory<Void> {

    public SlotProperty(String name, ItemStack itemStack, Consumer<ItemStack> update) {
        super(name, null, aVoid -> {
        });
        this.getNode().getChildren().remove(1);
        Button actionButton;
        if (itemStack.isEmpty()) {
            this.getNode().setPadding(Insets.left(30));
            this.addAll(actionButton = new Button("Open Clipboard"));
            actionButton.getOnMouseClickedListeners().add(e ->
                    new SelectionClipboard<ItemClipboardEntry>(AbstractClipboard.Filter.ITEM, item ->
                            update.accept(item.getItemStack().copy())));
            actionButton.setPrefWidth(120);
        } else {
            TexturedButton itemButton;
            TexturedButton removeButton;
            this.getNode().getChildren().add(0, itemButton = new TexturedButton(itemStack));
            itemButton.setMargin(Insets.right(5));
            this.addAll(
                    actionButton = new Button("Open Item Editor"),
                    removeButton = new TexturedButton("delete.png")
            );
            actionButton.getOnMouseClickedListeners().add(e ->
                    new ItemEditor(itemStack, null, update));
            removeButton.getOnMouseClickedListeners().add(e -> update.accept(ItemStack.EMPTY));
            actionButton.setPrefWidth(100);
        }
    }

    @Override
    protected Void getValue() {
        return null;
    }

    @Override
    protected void setValue(Void value) {
    }

    @Override
    public void updateSize(int listWidth) {
        nameLabel.setPrefWidth(listWidth - 189);
    }
}
