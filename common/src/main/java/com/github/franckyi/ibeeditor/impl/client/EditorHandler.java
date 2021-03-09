package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.EntityEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.model.NBTEditor;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorConfiguration;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class EditorHandler {
    public static void openItemEditor(Item item, Consumer<Item> action) {
        openEditor(mvc(IBEEditorMVC.EDITOR, new ItemEditorModel(item, action)));
    }

    public static void openBlockEditor(Block block, Consumer<Block> action) {
        openEditor(mvc(IBEEditorMVC.EDITOR, new BlockEditorModel(block, action)));
    }

    public static void openEntityEditor(Entity entity, Consumer<Entity> action) {
        openEditor(mvc(IBEEditorMVC.EDITOR, new EntityEditorModel(entity, action)));
    }

    public static void openNBTEditor(CompoundTag tag, Consumer<CompoundTag> action) {
        openEditor(mvc(IBEEditorMVC.NBT_EDITOR, new NBTEditor(tag, action)));
    }

    private static void openEditor(Node root) {
        Minecraft.getClient().getScreenHandler().showScene(scene(root, true, true).show(scene -> {
            Minecraft.getClient().getScreenScaling().setBaseScale(IBEEditorConfiguration.CLIENT.getEditorScale());
            scene.widthProperty().addListener(Minecraft.getClient().getScreenScaling()::refresh);
            scene.heightProperty().addListener(Minecraft.getClient().getScreenScaling()::refresh);
        }).hide(scene -> {
            IBEEditorConfiguration.CLIENT.setEditorScale(Minecraft.getClient().getScreenScaling().getScaleAndReset());
            IBEEditorConfiguration.saveClient();
        }));
    }
}
