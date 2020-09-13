package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.ibeeditor.client.ClientUtils;
import com.github.franckyi.ibeeditor.client.EntityIcons;
import com.github.franckyi.ibeeditor.client.clipboard.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.gui.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.gui.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.entity.EntityEditor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

import java.util.function.Consumer;

public class SpawnEggCategory extends AbstractCategory {
    private final ItemStack itemStack;
    private final SpawnEggItem item;
    private CompoundNBT spawnData;

    public SpawnEggCategory(ItemStack itemStack, SpawnEggItem item) {
        this.itemStack = itemStack;
        this.item = item;
        spawnData = itemStack.getOrCreateChildTag("EntityTag");
        this.addEntityProperty();
    }

    private void addEntityProperty() {
        this.getChildren().add(0, new EntityProperty(EntityType.byKey(spawnData.getString("id"))
                .orElse(item.getType(itemStack.getTag())), spawnData, this::setEntity, this::setEntity));
    }

    private void setEntity(EntityClipboardEntry entry) {
        this.setData(entry.getEntityTag());
    }

    private void setEntity(Entity entity) {
        this.setData(ClientUtils.getCleanEntityTag(entity));
    }

    private void setData(CompoundNBT tag) {
        spawnData = tag;
        this.getChildren().remove(0);
        this.addEntityProperty();
    }

    @Override
    public void apply() {
        super.apply();
        itemStack.getOrCreateTag().put("EntityTag", spawnData);
    }

    private static class EntityProperty extends AbstractProperty<Void> {

        private final Label entityLabel;

        public EntityProperty(EntityType<?> entityType, CompoundNBT spawnData, Consumer<EntityClipboardEntry> action0, Consumer<Entity> action1) {
            super(null, aVoid -> {
            });
            this.getNode().setAlignment(Pos.LEFT);
            this.getNode().getChildren().remove(0);
            Button editButton;
            Button replaceButton;
            this.addAll(
                    entityLabel = new Label("Entity"),
                    EntityIcons.createTexturedButtonForEntity(entityType),
                    editButton = new Button("Editor", "Open in Entity Editor"),
                    replaceButton = new Button("Clipboard", "Replace with Clipboard...")
            );
            editButton.getOnMouseClickedListeners().add(e ->
                    new EntityEditor(ClientUtils.createEntity(entityType, spawnData), action1));
            replaceButton.getOnMouseClickedListeners().add(e ->
                    new SelectionClipboard<>(AbstractClipboard.Filter.ENTITY, action0));
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
        }

        @Override
        public void updateSize(int listWidth) {
            entityLabel.setPrefWidth(listWidth - OFFSET - 155);
        }
    }
}
