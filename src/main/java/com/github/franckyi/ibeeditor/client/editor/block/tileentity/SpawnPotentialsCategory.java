package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.IntegerField;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.clipboard.AbstractClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.SelectionClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.logic.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.editor.common.AbstractProperty;
import com.github.franckyi.ibeeditor.client.editor.common.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.editor.common.property.IEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.editor.entity.EntityEditor;
import com.github.franckyi.ibeeditor.client.util.ClientUtils;
import com.github.franckyi.ibeeditor.client.util.EntityIcons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SpawnPotentialsCategory extends EditableCategory<SpawnPotentialsCategory.SpawnPotentialModel> {

    private final TileEntityMobSpawner tileEntity;
    private final List<SpawnPotentialModel> spawnPotentialList;
    private final NBTTagCompound tag;
    private NBTTagList spawnPotentialsTag;

    public SpawnPotentialsCategory(TileEntityMobSpawner tileEntity) {
        this.tileEntity = tileEntity;
        spawnPotentialList = new ArrayList<>();
        tag = new NBTTagCompound();
        tileEntity.write(tag);
        spawnPotentialsTag = tag.getList("SpawnPotentials", Constants.NBT.TAG_COMPOUND);
        this.getChildren().add(new AddButton("Add spawn potential"));
        for (int i = 0; i < spawnPotentialsTag.size(); i++) {
            NBTTagCompound spawnPotential = spawnPotentialsTag.getCompound(i);
            this.addProperty(new SpawnPotentialModel(spawnPotential.getInt("Weight"), spawnPotential.getCompound("Entity")));
        }
    }

    @Override
    protected AbstractProperty<SpawnPotentialModel> createNewProperty(SpawnPotentialModel initialValue, int index) {
        return new SpawnPotentialProperty(index, initialValue, spawnPotentialList::add);
    }

    @Override
    protected SpawnPotentialModel getDefaultPropertyValue() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.putString("id", "minecraft:pig");
        return new SpawnPotentialModel(0, tag);
    }

    @Override
    public void apply() {
        tileEntity.write(tag);
        spawnPotentialsTag = tag.getList("SpawnPotentials", Constants.NBT.TAG_COMPOUND);
        spawnPotentialList.clear();
        spawnPotentialsTag.clear();
        super.apply();
        spawnPotentialList.forEach(spawnPotential -> {
            NBTTagCompound tag = new NBTTagCompound();
            tag.putInt("Weight", spawnPotential.weight);
            tag.put("Entity", spawnPotential.tag);
            spawnPotentialsTag.add(tag);
        });
        tileEntity.read(tag);
    }

    private class SpawnPotentialProperty extends AbstractProperty<SpawnPotentialModel> implements IEditableCategoryProperty {

        private PropertyControls controls;
        private NBTTagCompound tag;

        private TexturedButton entityButton;
        private Label weightLabel;
        private IntegerField weightField;
        private Button editButton;
        private Button replaceButton;

        public SpawnPotentialProperty(int index, SpawnPotentialModel initialValue, Consumer<SpawnPotentialModel> action) {
            super(initialValue, action);
            controls = new PropertyControls(SpawnPotentialsCategory.this, index);
            IEditableCategoryProperty.super.build();
        }

        @Override
        protected SpawnPotentialModel getValue() {
            return new SpawnPotentialModel(weightField.getValue(), tag);
        }

        @Override
        protected void setValue(SpawnPotentialModel value) {
            tag = value.tag;
            weightField.setValue(value.weight);
            updateEntityButton();
        }

        @Override
        public void build() {
            this.getNode().setAlignment(Pos.LEFT);
            this.addAll(
                    entityButton = new TexturedButton(Items.AIR.getRegistryName()),
                    weightLabel = new Label("Weight :"),
                    weightField = new IntegerField(),
                    editButton = new Button("Editor", "Open in Entity Editor"),
                    replaceButton = new Button("Clipboard", "Replace with Clipboard...")
            );
            weightLabel.setMargin(Insets.horizontal(5));
            editButton.getOnMouseClickedListeners().add(e ->
                    new EntityEditor(ClientUtils.createEntity(tag), this::updateEntity));
            replaceButton.getOnMouseClickedListeners().add(e ->
                    new SelectionClipboard<EntityClipboardEntry>(AbstractClipboard.Filter.ENTITY, this::updateEntity));
        }

        private void updateEntity(EntityClipboardEntry entry) {
            tag = entry.getEntityTag();
            this.updateEntityButton();
        }

        private void updateEntity(Entity entity) {
            tag = ClientUtils.getCleanEntityTag(entity);
            this.updateEntityButton();
        }

        private void updateEntityButton() {
            EntityType<?> entityType = EntityType.getById(tag.getString("id"));
            ((TexturedButton.GuiTexturedButtonView) entityButton.getView()).setTexture(EntityIcons.getHeadFromEntityType(entityType));
            entityButton.getText().clear();
            entityButton.getText().add(entityType.getName().getFormattedText());
        }

        @Override
        public PropertyControls getControls() {
            return controls;
        }

        @Override
        public void updateSize(int listWidth) {
            weightField.setPrefWidth(listWidth - 239);
        }
    }

    protected class SpawnPotentialModel {

        private int weight;
        private NBTTagCompound tag;

        private SpawnPotentialModel(int weight, NBTTagCompound tag) {
            this.weight = weight;
            this.tag = tag;
        }
    }
}
