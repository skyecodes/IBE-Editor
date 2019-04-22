package com.github.franckyi.ibeeditor.client.editor.block.tileentity;

import com.github.franckyi.ibeeditor.client.editor.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.editor.block.TileEntityCategory;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class MobSpawnerCategory extends TileEntityCategory<TileEntityMobSpawner> {

    public MobSpawnerCategory(BlockEditor editor, TileEntityMobSpawner tileEntityMobSpawner) {
        super(editor, tileEntityMobSpawner);
    }

}
