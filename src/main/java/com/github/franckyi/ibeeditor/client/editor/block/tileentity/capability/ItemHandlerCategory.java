package com.github.franckyi.ibeeditor.client.editor.block.tileentity.capability;

import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.logic.ItemClipboardEntry;
import com.github.franckyi.ibeeditor.client.editor.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.client.editor.property.LabeledCategory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.HashMap;
import java.util.Map;

public class ItemHandlerCategory extends TileEntityCategory<IItemHandler> {

    private final Map<Integer, ItemStack> inventory;

    public ItemHandlerCategory(BlockEditor editor, IItemHandler handler) {
        super(editor, handler);
        inventory = new HashMap<>();
        for (int i = 0; i < handler.getSlots(); i++) {
            inventory.put(i, handler.getStackInSlot(i).copy());
        }
        build();
    }

    private void build() {
        inventory.forEach((slot, itemStack) -> this.addAll(new SlotProperty(slot, itemStack)));
    }

    @Override
    public void apply() {
        inventory.forEach((slot, itemStack) -> {
            t.extractItem(slot, Integer.MAX_VALUE, false);
            t.insertItem(slot, itemStack, false);
        });
    }

    private void update(int slot, ItemStack itemStack) {
        inventory.put(slot, itemStack);
        this.getChildren().clear();
        build();
        this.getChildren().forEach(p -> p.updateSize(this.getWidth()));
    }

    private class SlotProperty extends LabeledCategory<Void> {

        private SlotProperty(int slot, ItemStack itemStack) {
            super("", null, aVoid -> {
            });
            this.getNode().getChildren().remove(1);
            Button actionButton;
            if (itemStack.isEmpty()) {
                nameLabel.setText("Slot #" + slot);
                this.addAll(actionButton = new Button("Open Clipboard"));
                actionButton.getOnMouseClickedListeners().add(e ->
                        new SelectionClipboard<ItemClipboardEntry>(AbstractClipboard.Filter.ITEM, item ->
                                update(slot, item.getItemStack().copy())));
                actionButton.setPrefWidth(120);
            } else {
                Button removeButton;
                nameLabel.setText("Slot #" + slot + " : "
                        + itemStack.getDisplayName().getFormattedText());
                this.addAll(
                        actionButton = new Button("Open Item Editor"),
                        removeButton = new TexturedButton("delete.png")
                );
                actionButton.getOnMouseClickedListeners().add(e ->
                        new ItemEditor(itemStack, null, is -> update(slot, is)));
                removeButton.getOnMouseClickedListeners().add(e -> update(slot, ItemStack.EMPTY));
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
            nameLabel.setPrefWidth(listWidth - 164);
        }
    }
}
