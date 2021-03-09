package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.util.common.TextFormatting;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class IBEEditorServer {
    private static final Marker MARKER = MarkerManager.getMarker("Server");
    private static final Set<UUID> allowedPlayers = new HashSet<>();
    private static final Text MUST_INSTALL = text("You must install IBE Editor in order to use this command.", TextFormatting.RED);
    private static final Text DOWNLOAD = link("Click here to download the mod!", "https://www.curseforge.com/minecraft/mc-mods/ibe-editor", TextFormatting.AQUA, TextFormatting.UNDERLINE);

    public static void notifyClient(Player player) {
        Minecraft.getLogger().debug(MARKER, "Notifying {} that this server has IBE Editor", player.getName());
        ServerNetworkEmitter.notifyClient(player);
    }

    public static void removeAllowedPlayer(Player player) {
        Minecraft.getLogger().debug(MARKER, "Removing {} from allowed players", player.getName());
        allowedPlayers.remove(player.getProfileId());
    }

    public static void addAllowedPlayer(Player player) {
        Minecraft.getLogger().debug(MARKER, "Adding {} to allowed players", player.getName());
        allowedPlayers.add(player.getProfileId());
    }

    public static boolean isPlayerAllowed(Player player) {
        return allowedPlayers.contains(player.getProfileId());
    }

    public static <S> void registerCommand(CommandDispatcher<S> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.<S>literal("ibe").executes(createCommand(p -> triggerOpenWorldEditor(p, false)))
                        .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> triggerOpenWorldEditor(p, true)))
                                .then(LiteralArgumentBuilder.<S>literal("item").executes(createCommand(p -> triggerOpenItemEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("block").executes(createCommand(p -> triggerOpenBlockEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("entity").executes(createCommand(p -> triggerOpenEntityEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("self").executes(createCommand(p -> triggerOpenSelfEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("item").executes(createCommand(p -> triggerOpenItemEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> triggerOpenItemEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("block").executes(createCommand(p -> triggerOpenBlockEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> triggerOpenBlockEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("entity").executes(createCommand(p -> triggerOpenEntityEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> triggerOpenEntityEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("self").executes(createCommand(p -> triggerOpenSelfEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> triggerOpenSelfEditor(p, true)))))
        );
    }

    @SuppressWarnings("unchecked")
    private static <S> Command<S> createCommand(Function<Player, Integer> command) {
        return (Command<S>) Minecraft.getCommon().createCommand(command);
    }

    private static int triggerOpenWorldEditor(Player player, boolean nbt) {
        Minecraft.getLogger().debug(MARKER, "Triggering World Editor (nbt={}) for {}", nbt, player.getName());
        return triggerOpenEditor(player, nbt, ServerNetworkEmitter::triggerOpenWorldEditor);
    }

    private static int triggerOpenItemEditor(Player player, boolean nbt) {
        Minecraft.getLogger().debug(MARKER, "Triggering Item Editor (nbt={}) for {}", nbt, player.getName());
        return triggerOpenEditor(player, nbt, ServerNetworkEmitter::triggerOpenItemEditor);
    }

    private static int triggerOpenBlockEditor(Player player, boolean nbt) {
        Minecraft.getLogger().debug(MARKER, "Triggering Block Editor (nbt={}) for {}", nbt, player.getName());
        return triggerOpenEditor(player, nbt, ServerNetworkEmitter::triggerOpenBlockEditor);
    }

    private static int triggerOpenEntityEditor(Player player, boolean nbt) {
        Minecraft.getLogger().debug(MARKER, "Triggering Entity Editor (nbt={}) for {}", nbt, player.getName());
        return triggerOpenEditor(player, nbt, ServerNetworkEmitter::triggerOpenEntityEditor);
    }

    private static int triggerOpenSelfEditor(Player player, boolean nbt) {
        Minecraft.getLogger().debug(MARKER, "Triggering Self Editor (nbt={}) for {}", nbt, player.getName());
        return triggerOpenEditor(player, nbt, ServerNetworkEmitter::triggerOpenSelfEditor);
    }

    private static int triggerOpenEditor(Player player, boolean nbt, BiConsumer<Player, Boolean> action) {
        if (isPlayerAllowed(player)) {
            action.accept(player, nbt);
            return 0;
        }
        player.sendMessage(MUST_INSTALL);
        player.sendMessage(DOWNLOAD);
        return 1;
    }

    public static void updatePlayerMainHandItem(Player player, Item item) {
        player.setItemMainHand(item);
        player.sendMessage(text("Item updated!", GREEN));
    }

    public static void updatePlayerInventoryItem(Player player, Item item, int slotId) {
        player.setInventoryItem(slotId, item);
        player.sendMessage(text("Item updated!", GREEN));
    }

    public static void updateBlockInventoryItem(Player player, Item item, int slotId, BlockPos blockPos) {
        player.getWorld().setBlockInventoryItem(blockPos, slotId, item);
        player.sendMessage(text("Item updated!", GREEN));
    }

    public static void updateBlock(Player sender, Block block, BlockPos blockPos) {
        sender.getWorld().setBlockData(blockPos, block);
        sender.sendMessage(text("Block updated!", GREEN));
    }

    public static void updateEntity(Player sender, Entity entity, int entityId) {
        sender.getWorld().setEntityData(entityId, entity);
        sender.sendMessage(text("Entity updated!", GREEN));
    }
}
