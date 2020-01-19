package com.github.franckyi.ibeeditor.client.gui.editor.block.tileentity;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.ClientUtils;
import com.github.franckyi.ibeeditor.client.EntityIcons;
import com.github.franckyi.ibeeditor.client.clipboard.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.gui.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.gui.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyInteger;
import com.github.franckyi.ibeeditor.client.gui.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.entity.EntityEditor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;

import java.util.function.Consumer;

public class MobSpawnerCategory extends TileEntityCategory<MobSpawnerTileEntity> {

    private final CompoundNBT tag;
    private CompoundNBT spawnData;

    public MobSpawnerCategory(MobSpawnerTileEntity tileEntityMobSpawner) {
        super(tileEntityMobSpawner);
        tag = t.write(new CompoundNBT());
        spawnData = tag.getCompound("SpawnData");
        this.addEntityProperty();
        this.add("Spawn count", "SpawnCount");
        this.add("Spawn range", "SpawnRange");
        this.add("Required player range", "RequiredPlayerRange");
        this.add("Delay", "Delay");
        this.add("Min spawn delay", "MinSpawnDelay");
        this.add("Max spawn delay", "MaxSpawnDelay");
        this.add("Max nearby entities", "MaxNearbyEntities");
    }

    private void addEntityProperty() {
        this.getChildren().add(0, new EntityProperty(EntityType.byKey(spawnData.getString("id")).orElse(EntityType.PIG), spawnData, this::setEntity, this::setEntity));
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

    private void add(String name, String tagName) {
        this.getChildren().add(new PropertyInteger(name, tag.getInt(tagName), i -> tag.putInt(tagName, i), 150));
    }

    @Override
    public void apply() {
        super.apply();
        tag.put("SpawnData", spawnData);
        t.read(tag);
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
                    entityLabel = new Label("Next spawn :"),
                    new TexturedButton(EntityIcons.getHeadFromEntityType(entityType), entityType.getName().getFormattedText()),
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
