package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.logic.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.editor.common.AbstractProperty;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyInteger;
import com.github.franckyi.ibeeditor.client.editor.entity.EntityEditor;
import com.github.franckyi.ibeeditor.client.util.ClientUtils;
import com.github.franckyi.ibeeditor.client.util.EntityIcons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;

import java.util.function.Consumer;

public class MobSpawnerCategory extends TileEntityCategory<TileEntityMobSpawner> {

    private final NBTTagCompound tag;
    private NBTTagCompound spawnData;

    public MobSpawnerCategory(TileEntityMobSpawner tileEntityMobSpawner) {
        super(tileEntityMobSpawner);
        tag = t.write(new NBTTagCompound());
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
        this.getChildren().add(0, new EntityProperty(EntityType.getById(spawnData.getString("id")), spawnData, this::setEntity, this::setEntity));
    }

    private void setEntity(EntityClipboardEntry entry) {
        this.setData(entry.getEntityTag());
    }

    private void setEntity(Entity entity) {
        this.setData(ClientUtils.getCleanEntityTag(entity));
    }

    private void setData(NBTTagCompound tag) {
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

    private class EntityProperty extends AbstractProperty<Void> {

        private final Label entityLabel;

        public EntityProperty(EntityType<?> entityType, NBTTagCompound spawnData, Consumer<EntityClipboardEntry> action0, Consumer<Entity> action1) {
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
            entityLabel.setPrefWidth(listWidth - 157);
        }
    }
}
