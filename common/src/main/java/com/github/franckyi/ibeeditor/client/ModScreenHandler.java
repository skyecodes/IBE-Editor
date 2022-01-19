package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Scene;
import com.github.franckyi.ibeeditor.client.context.BlockEditorContext;
import com.github.franckyi.ibeeditor.client.context.EditorContext;
import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.client.context.ItemEditorContext;
import com.github.franckyi.ibeeditor.client.screen.model.*;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ListSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ListSelectionElementModel;
import com.github.franckyi.ibeeditor.client.screen.mvc.*;
import com.github.franckyi.ibeeditor.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.common.EditorType;
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

    private static void openScaledScreen(Node root) {
        openScaledScreen(root, false);
    }

    private static void openScaledScreen(Node root, boolean replace) {
        Consumer<Scene> action = replace ? Guapi.getScreenHandler()::replaceScene : Guapi.getScreenHandler()::showScene;
        try {
            action.accept(scene(root, true, true).show(scene -> {
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

    public static void openEditor(EditorType editorType, EditorContext<?> context) {
        openEditor(editorType, context, false);
    }

    public static void openEditor(EditorType editorType, EditorContext<?> context, boolean replace) {
        openScaledScreen(switch (editorType) {
            case STANDARD -> {
                if (context instanceof ItemEditorContext ctx) {
                    yield mvc(StandardEditorMVC.INSTANCE, new ItemEditorModel(ctx));
                } else if (context instanceof BlockEditorContext ctx) {
                    yield mvc(StandardEditorMVC.INSTANCE, new BlockEditorModel(ctx));
                } else if (context instanceof EntityEditorContext ctx) {
                    yield mvc(StandardEditorMVC.INSTANCE, new EntityEditorModel(ctx));
                } else {
                    throw new IllegalStateException("context should be an instance of ItemEditorContext, BlockEditorContext or EntityEditorContext");
                }
            }
            case NBT -> mvc(NBTEditorMVC.INSTANCE, new NBTEditorModel(context));
            case SNBT -> mvc(SNBTEditorMVC.INSTANCE, new SNBTEditorModel(context));
        }, replace);
    }
}
