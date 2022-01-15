package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.client.screen.model.*;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ListSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ListSelectionElementModel;
import com.github.franckyi.ibeeditor.client.screen.mvc.*;
import com.github.franckyi.ibeeditor.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.common.EditorContext;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public final class ModScreenHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void openSettingsScreen() {
        openScaledScreen(mvc(ConfigEditorMVC.INSTANCE, new ConfigEditorScreenModel()));
    }

    public static void openListSelectionScreen(MutableComponent title, String attributeName, List<? extends ListSelectionElementModel> items, Consumer<String> action) {
        openScaledScreen(mvc(ListSelectionScreenMVC.INSTANCE, new ListSelectionScreenModel(title, attributeName, items, action)));
    }

    public static void openColorSelectionScreen(ColorSelectionScreenModel.Target target, int color, Consumer<String> action) {
        openScaledScreen(mvc(ColorSelectionScreenMVC.INSTANCE, new ColorSelectionScreenModel(target, action, color)));
    }

    public static void openVault() {
        openScaledScreen(mvc(VaultScreenMVC.INSTANCE, new VaultScreenModel()));
    }

    public static void openEditor(EditorContext context) {
        openScaledScreen(switch (context.getEditorType()) {
            case STANDARD -> mvc(StandardEditorMVC.INSTANCE, switch (context.getTarget()) {
                case ITEM -> new ItemEditorModel(context);
                case BLOCK -> new BlockEditorModel(context);
                case ENTITY -> new EntityEditorModel(context);
            });
            case NBT -> mvc(NBTEditorMVC.INSTANCE, new NBTEditorModel(context));
            case SNBT -> mvc(SNBTEditorMVC.INSTANCE, new SNBTEditorModel(context));
        });
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
            ClientUtil.showMessage(ModTexts.Messages.ERROR_GENERIC);
        }
    }
}
