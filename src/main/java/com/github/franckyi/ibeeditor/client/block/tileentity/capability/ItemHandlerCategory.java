package com.github.franckyi.ibeeditor.client.block.tileentity.capability;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.ibeeditor.client.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.item.ItemEditor;
import com.github.franckyi.ibeeditor.client.property.LabeledCategory;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.IItemHandler;

public class ItemHandlerCategory extends TileEntityCategory<IItemHandler> {

    public ItemHandlerCategory(BlockEditor editor, IItemHandler handler) {
        super(editor, handler);
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                this.getChildren().add(new SlotProperty(i, stack, editor.getBlockPos()));
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

    private class SlotProperty extends LabeledCategory<Void> {

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
