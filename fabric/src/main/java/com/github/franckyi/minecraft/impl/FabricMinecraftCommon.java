package com.github.franckyi.minecraft.impl;

import com.github.franckyi.minecraft.api.MinecraftCommon;
import com.github.franckyi.minecraft.api.common.network.Network;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.TagFactory;
import com.github.franckyi.minecraft.api.common.text.TextFactory;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.impl.common.nbt.FabricTagFactory;
import com.github.franckyi.minecraft.impl.common.network.FabricNetwork;
import com.github.franckyi.minecraft.impl.common.text.FabricTextFactory;
import com.github.franckyi.minecraft.impl.common.world.FabricBlock;
import com.github.franckyi.minecraft.impl.common.world.FabricEntity;
import com.github.franckyi.minecraft.impl.common.world.FabricItem;
import com.github.franckyi.minecraft.impl.common.world.FabricPlayer;
import com.mojang.brigadier.Command;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;

import java.nio.file.Path;
import java.util.function.Function;

public final class FabricMinecraftCommon implements MinecraftCommon {
    public static final MinecraftCommon INSTANCE = new FabricMinecraftCommon();

    private FabricMinecraftCommon() {
    }

    @Override
    public TextFactory getTextFactory() {
        return FabricTextFactory.INSTANCE;
    }

    @Override
    public TagFactory getTagFactory() {
        return FabricTagFactory.INSTANCE;
    }

    @Override
    public Item createItem(CompoundTag tag) {
        return new FabricItem(tag);
    }

    @Override
    public Block createBlock(CompoundTag state, CompoundTag data) {
        return new FabricBlock(state, data);
    }

    @Override
    public Entity createEntity(CompoundTag tag) {
        return new FabricEntity(tag);
    }

    @Override
    public Network getNetwork() {
        return FabricNetwork.INSTANCE;
    }

    @Override
    public Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }

    @Override
    public Command<ServerCommandSource> createCommand(Function<Player, Integer> command) {
        return ctx -> command.apply(new FabricPlayer(ctx.getSource().getPlayer()));
    }
}
