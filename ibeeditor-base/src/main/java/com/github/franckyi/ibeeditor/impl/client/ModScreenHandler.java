package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.ConfigEditorModelImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.model.NBTEditor;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.EntityEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.ItemEditorModel;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ModScreenHandler {
    public static void openItemEditorScreen(Item item, Consumer<Item> action, Text disabledTooltip) {
        openScaledScreen(mvc(IBEEditorMVC.STANDARD_EDITOR, new ItemEditorModel(item, action, disabledTooltip)));
    }

    public static void openBlockEditorScreen(Block block, Consumer<Block> action, Text disabledTooltip) {
        openScaledScreen(mvc(IBEEditorMVC.STANDARD_EDITOR, new BlockEditorModel(block, action, disabledTooltip)));
    }

    public static void openEntityEditorScreen(Entity entity, Consumer<Entity> action, Text disabledTooltip) {
        openScaledScreen(mvc(IBEEditorMVC.STANDARD_EDITOR, new EntityEditorModel(entity, action, disabledTooltip)));
    }

    public static void openNBTEditorScreen(CompoundTag tag, Consumer<CompoundTag> action, Text disabledTooltip) {
        openScaledScreen(mvc(IBEEditorMVC.NBT_EDITOR, new NBTEditor(tag, action, disabledTooltip)));
    }

    public static void openSettingsScreen() {
        openScaledScreen(mvc(IBEEditorMVC.CONFIG_EDITOR, new ConfigEditorModelImpl()));
    }

    private static void openScaledScreen(Node root) {
        Guapi.getScreenHandler().showScene(scene(root, true, true).show(scene -> {
            Game.getClient().getScreenScaling().setBaseScale(ClientConfiguration.INSTANCE.getEditorScale());
            scene.widthProperty().addListener(Game.getClient().getScreenScaling()::refresh);
            scene.heightProperty().addListener(Game.getClient().getScreenScaling()::refresh);
        }).hide(scene -> {
            ClientConfiguration.INSTANCE.setEditorScale(Game.getClient().getScreenScaling().getScaleAndReset());
            ClientConfiguration.save();
        }));
    }
}
