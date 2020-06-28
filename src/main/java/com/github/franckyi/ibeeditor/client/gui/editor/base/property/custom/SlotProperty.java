package com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.clipboard.ItemClipboardEntry;
import com.github.franckyi.ibeeditor.client.gui.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.gui.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.LabeledProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.item.ItemEditor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;

public class SlotProperty extends LabeledProperty<Void> {
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
            itemButton.setMargin(Insets.right(9));
            this.addAll(
                    actionButton = new Button("Open Item Editor"),
                    removeButton = new TexturedButton("delete.png", TextFormatting.RED + "Remove")
            );
            actionButton.getOnMouseClickedListeners().add(e ->
                    ItemEditor.withConsumer(itemStack, update));
            removeButton.getOnMouseClickedListeners().add(e -> update.accept(ItemStack.EMPTY));
            actionButton.setPrefWidth(99);
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
        nameLabel.setPrefWidth(listWidth - OFFSET - 191);
    }
}
