package com.github.franckyi.ibeeditor.editor.block;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.ibeeditor.editor.Category;
import com.github.franckyi.ibeeditor.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.editor.property.EmptyProperty;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class InventoryCategory extends Category {

    public InventoryCategory(BlockEditor editor, IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (!itemStack.isEmpty()) {
                this.getChildren().add(new SlotProperty(i, itemStack, editor.getBlockPos()));
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        if (this.getChildren().isEmpty()) {
            mc.currentScreen.drawCenteredString(mc.fontRenderer, "The inventory is empty !", this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() / 2 - 5, TextFormatting.DARK_RED.getColor());
        }
    }

    private class SlotProperty extends EmptyProperty<Void> {

        private Button openItem;

        public SlotProperty(int slot, ItemStack itemStack, BlockPos blockPos) {
            super("Slot #" + slot + " - " + itemStack.getDisplayName().getFormattedText(), null, aVoid -> {
            });
            this.getNode().getChildren().remove(2);
            nameLabel.setPrefWidth(Node.COMPUTED_SIZE);
            openItem.getOnMouseClickedListeners().add(e ->
                    new ItemEditor(itemStack, is ->
                            new BlockInventoryItemEditorMessage(is, blockPos, slot)));
        }

        @Override
        protected Void getValue() {
            return null;
        }

        @Override
        protected void setValue(Void value) {
        }

        @Override
        protected void build() {
            super.build();
            this.addAll(openItem = new Button("Open Item Editor"));
        }
    }
}
