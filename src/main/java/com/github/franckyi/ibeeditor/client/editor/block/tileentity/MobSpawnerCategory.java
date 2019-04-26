package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.editor.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import com.github.franckyi.ibeeditor.client.editor.property.PropertyInteger;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class MobSpawnerCategory extends TileEntityCategory<TileEntityMobSpawner> {

    private final NBTTagCompound tag;

    public MobSpawnerCategory(BlockEditor editor, TileEntityMobSpawner tileEntityMobSpawner) {
        super(editor, tileEntityMobSpawner);
        tag = t.write(new NBTTagCompound());
        this.add("Spawn count", "SpawnCount");
        this.add("Spawn range", "SpawnRange");
        this.add("Required player range", "RequiredPlayerRange");
        this.add("Delay", "Delay");
        this.add("Min spawn delay", "MinSpawnDelay");
        this.add("Max spawn delay", "MaxSpawnDelay");
        this.add("Max nearby entities", "MaxNearbyEntities");
    }

    private void add(String name, String tagName) {
        this.getChildren().add(new PropertyInteger(name, tag.getInt(tagName), i -> tag.putInt(tagName, i), 150));
    }

    @Override
    public void apply() {
        super.apply();
        t.read(tag);
    }
}
