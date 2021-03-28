package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.EntityEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.model.NBTEditor;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class EditorScreenHandler {
    public static void openItemEditor(Item item, Consumer<Item> action, Text disabledTooltip) {
        openEditor(mvc(IBEEditorMVC.EDITOR, new ItemEditorModel(item, action, disabledTooltip)));
    }

    public static void openBlockEditor(Block block, Consumer<Block> action, Text disabledTooltip) {
        openEditor(mvc(IBEEditorMVC.EDITOR, new BlockEditorModel(block, action, disabledTooltip)));
    }

    public static void openEntityEditor(Entity entity, Consumer<Entity> action, Text disabledTooltip) {
        openEditor(mvc(IBEEditorMVC.EDITOR, new EntityEditorModel(entity, action, disabledTooltip)));
    }

    public static void openNBTEditor(CompoundTag tag, Consumer<CompoundTag> action, Text disabledTooltip) {
        openEditor(mvc(IBEEditorMVC.NBT_EDITOR, new NBTEditor(tag, action, disabledTooltip)));
    }

    private static void openEditor(Node root) {
        Minecraft.getClient().getScreenHandler().showScene(scene(root, true, true).show(scene -> {
            Minecraft.getClient().getScreenScaling().setBaseScale(ClientConfiguration.INSTANCE.getEditorScale());
            scene.widthProperty().addListener(Minecraft.getClient().getScreenScaling()::refresh);
            scene.heightProperty().addListener(Minecraft.getClient().getScreenScaling()::refresh);
        }).hide(scene -> {
            ClientConfiguration.INSTANCE.setEditorScale(Minecraft.getClient().getScreenScaling().getScaleAndReset());
            ClientConfiguration.save();
        }));
    }
}
