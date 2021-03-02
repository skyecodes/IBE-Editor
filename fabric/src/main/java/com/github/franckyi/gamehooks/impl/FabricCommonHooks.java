package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.impl.common.*;
import com.github.franckyi.gamehooks.impl.common.tag.FabricTagFactory;
import com.mojang.brigadier.Command;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;

import java.nio.file.Path;
import java.util.function.Function;

public final class FabricCommonHooks implements CommonHooks {
    public static final CommonHooks INSTANCE = new FabricCommonHooks();

    private FabricCommonHooks() {
    }

    @Override
    public TextFactory textFactory() {
        return FabricTextFactory.INSTANCE;
    }

    @Override
    public TagFactory tagFactory() {
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
    public Command<ServerCommandSource> command(Function<Player, Integer> command) {
        return ctx -> command.apply(new FabricPlayer(ctx.getSource().getPlayer()));
    }
}
