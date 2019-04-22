package com.github.franckyi.ibeeditor.client.editor.entity;

import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.logic.ItemClipboardEntry;
import com.github.franckyi.ibeeditor.client.editor.Category;
import com.github.franckyi.ibeeditor.client.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.client.editor.property.LabeledCategory;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class InventoryEntityCategory extends Category {

    private final EntityLiving entity;
    private final Map<EntityEquipmentSlot, ItemStack> inventory;

    public InventoryEntityCategory(EntityLiving entity) {
        this.entity = entity;
        inventory = new HashMap<>();
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            inventory.put(slot, entity.getItemStackFromSlot(slot).copy());
        }
        build();
    }

    private void build() {
        inventory.forEach((slot, itemStack) -> this.addAll(new SlotProperty(slot, itemStack)));
    }

    @Override
    public void apply() {
        inventory.forEach(entity::setItemStackToSlot);
    }

    private void update(EntityEquipmentSlot slot, ItemStack itemStack) {
        inventory.put(slot, itemStack);
        this.getChildren().clear();
        build();
        this.getChildren().forEach(p -> p.updateSize(this.getWidth()));
    }

    private class SlotProperty extends LabeledCategory<Void> {

        private SlotProperty(EntityEquipmentSlot slot, ItemStack itemStack) {
            super("", null, aVoid -> {
            });
            this.getNode().getChildren().remove(1);
            Button actionButton;
            if (itemStack.isEmpty()) {
                nameLabel.setText(StringUtils.capitalize(slot.getName()));
                this.addAll(actionButton = new Button("Open Clipboard"));
                actionButton.getOnMouseClickedListeners().add(e ->
                        new SelectionClipboard<ItemClipboardEntry>(AbstractClipboard.Filter.ITEM, item ->
                                update(slot, item.getItemStack().copy())));
                actionButton.setPrefWidth(120);
            } else {
                Button removeButton;
                nameLabel.setText(StringUtils.capitalize(slot.getName()) + " : "
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
