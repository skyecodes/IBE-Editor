package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.ConfigEditorModelImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.model.NBTEditor;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.EntityEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.ItemEditorModel;
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
        openScaledScreen(mvc(IBEEditorMVC.STANDARD_EDITOR, new ItemEditorModel(item, action, disabledTooltip)));
    }

    public static void openBlockEditor(Block block, Consumer<Block> action, Text disabledTooltip) {
        openScaledScreen(mvc(IBEEditorMVC.STANDARD_EDITOR, new BlockEditorModel(block, action, disabledTooltip)));
    }

    public static void openEntityEditor(Entity entity, Consumer<Entity> action, Text disabledTooltip) {
        openScaledScreen(mvc(IBEEditorMVC.STANDARD_EDITOR, new EntityEditorModel(entity, action, disabledTooltip)));
    }

    public static void openNBTEditor(CompoundTag tag, Consumer<CompoundTag> action, Text disabledTooltip) {
        openScaledScreen(mvc(IBEEditorMVC.NBT_EDITOR, new NBTEditor(tag, action, disabledTooltip)));
    }

    public static void openSettings() {
        openScaledScreen(mvc(IBEEditorMVC.CONFIG_EDITOR, new ConfigEditorModelImpl()));
    }

    private static void openScaledScreen(Node root) {
        GUAPI.setDebugMode(ClientConfiguration.INSTANCE.getGuapiDebugMode());
        GUAPI.setTheme(ClientConfiguration.INSTANCE.getGuapiTheme());
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
