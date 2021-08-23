package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.world.BlockData;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.base.client.mvc.*;
import com.github.franckyi.ibeeditor.base.client.mvc.model.*;
import com.github.franckyi.ibeeditor.base.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ModScreenHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void openItemEditorScreen(IItemStack itemStack, Consumer<IItemStack> action, IText disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new ItemEditorModel(itemStack, action, disabledTooltip)));
    }

    public static void openBlockEditorScreen(BlockData block, Consumer<BlockData> action, IText disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new BlockEditorModel(block, action, disabledTooltip)));
    }

    public static void openEntityEditorScreen(ICompoundTag entity, Consumer<ICompoundTag> action, IText disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new EntityEditorModel(entity, action, disabledTooltip)));
    }

    public static void openNBTEditorScreen(ICompoundTag tag, Consumer<ICompoundTag> action, IText disabledTooltip) {
        openScaledScreen(mvc(NBTEditorMVC.INSTANCE, new NBTEditorModel(tag, action, disabledTooltip)));
    }

    public static void openSNBTEditorScreen(String snbt, Consumer<String> action, IText disabledTooltip) {
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
                ScreenScalingManager.get().setBaseScale(ClientConfiguration.INSTANCE.getEditorScale());
                scene.widthProperty().addListener(ScreenScalingManager.get()::refresh);
                scene.heightProperty().addListener(ScreenScalingManager.get()::refresh);
            }).hide(scene -> {
                ClientConfiguration.INSTANCE.setEditorScale(ScreenScalingManager.get().getScaleAndReset());
                ClientConfiguration.save();
            }));
        } catch (Exception e) {
            LOGGER.error("Error while opening screen", e);
            IPlayer.client().sendMessage(ModTexts.prefixed(translated("ibeeditor.message.error_generic")).red());
        }
    }
}
