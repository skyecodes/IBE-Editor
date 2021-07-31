package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.base.client.mvc.*;
import com.github.franckyi.ibeeditor.base.client.mvc.model.*;
import com.github.franckyi.ibeeditor.base.common.Messages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ModScreenHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void openItemEditorScreen(Item item, Consumer<Item> action, Text disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new ItemEditorModel(item, action, disabledTooltip)));
    }

    public static void openBlockEditorScreen(Block block, Consumer<Block> action, Text disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new BlockEditorModel(block, action, disabledTooltip)));
    }

    public static void openEntityEditorScreen(Entity entity, Consumer<Entity> action, Text disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new EntityEditorModel(entity, action, disabledTooltip)));
    }

    public static void openNBTEditorScreen(CompoundTag tag, Consumer<CompoundTag> action, Text disabledTooltip) {
        openScaledScreen(mvc(NBTEditorMVC.INSTANCE, new NBTEditorModel(tag, action, disabledTooltip)));
    }

    public static void openSNBTEditorScreen(String snbt, Consumer<String> action, Text disabledTooltip) {
        openScaledScreen(mvc(SNBTEditorMVC.INSTANCE, new SNBTEditorModel(snbt, action, disabledTooltip)));
    }

    public static void openSettingsScreen() {
        openScaledScreen(mvc(ConfigEditorMVC.INSTANCE, new ConfigEditorModel()));
    }

    public static void openListSelectionScreen(String title, String attributeName, List<? extends ListSelectionItemModel> items, Consumer<String> action) {
        openScaledScreen(mvc(ListSelectionScreenMVC.INSTANCE, new ListSelectionScreenModel(title, attributeName, items, action)));
    }

    public static void openColorSelectionScreen(ColorSelectionScreenModel.Target target, int color, Consumer<String> action) {
        openScaledScreen(mvc(ColorSelectionScreenMVC.INSTANCE, new ColorSelectionScreenModel(target, action, color)));
    }

    private static void openScaledScreen(Node root) {
        try {
            Guapi.getScreenHandler().showScene(scene(root, true, true).show(scene -> {
                Game.getClient().getScreenScaling().setBaseScale(ClientConfiguration.INSTANCE.getEditorScale());
                scene.widthProperty().addListener(Game.getClient().getScreenScaling()::refresh);
                scene.heightProperty().addListener(Game.getClient().getScreenScaling()::refresh);
            }).hide(scene -> {
                ClientConfiguration.INSTANCE.setEditorScale(Game.getClient().getScreenScaling().getScaleAndReset());
                ClientConfiguration.save();
            }));
        } catch (Exception e) {
            LOGGER.error("Error while opening screen", e);
            Game.getClient().getPlayer().sendMessage(Messages.withPrefix(translated("ibeeditor.message.error_generic")).red());
        }
    }
}
