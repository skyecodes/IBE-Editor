package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.client.screen.model.*;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ListSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ListSelectionElementModel;
import com.github.franckyi.ibeeditor.client.screen.mvc.*;
import com.github.franckyi.ibeeditor.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public final class ModScreenHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void openItemEditorScreen(ItemStack itemStack, Consumer<ItemStack> action, Component disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new ItemEditorModel(itemStack, action, disabledTooltip)));
    }

    @Deprecated
    public static void openBlockEditorScreen(Pair<BlockState, CompoundTag> block, Consumer<Pair<BlockState, CompoundTag>> action, Component disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new BlockEditorModel(block, action, disabledTooltip)));
    }

    @Deprecated
    public static void openEntityEditorScreen(CompoundTag entity, Consumer<CompoundTag> action, Component disabledTooltip) {
        openScaledScreen(mvc(StandardEditorMVC.INSTANCE, new EntityEditorModel(entity, action, disabledTooltip)));
    }

    public static void openNBTEditorScreen(CompoundTag tag, Consumer<CompoundTag> action, Component disabledTooltip) {
        openScaledScreen(mvc(NBTEditorMVC.INSTANCE, new NBTEditorModel(tag, action, disabledTooltip)));
    }

    public static void openSNBTEditorScreen(String snbt, Consumer<String> action, Component disabledTooltip) {
        openScaledScreen(mvc(SNBTEditorMVC.INSTANCE, new SNBTEditorModel(snbt, action, disabledTooltip)));
    }

    public static void openSettingsScreen() {
        openScaledScreen(mvc(ConfigEditorMVC.INSTANCE, new ConfigEditorModel()));
    }

    public static void openListSelectionScreen(MutableComponent title, String attributeName, List<? extends ListSelectionElementModel> items, Consumer<String> action) {
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
            Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.ERROR_GENERIC, false);
        }
    }

    public static void optimize(PoseStack matrices) {
        scene(mvc(StandardEditorMVC.INSTANCE, new ItemEditorModel(new ItemStack(Items.AIR), item -> {
        }, null)), true, true).render(matrices, 0, 0, 0);
    }
}
