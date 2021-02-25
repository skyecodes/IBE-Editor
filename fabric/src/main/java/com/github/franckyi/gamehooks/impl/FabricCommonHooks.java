package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.*;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
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
    public Item createItemFromTag(ObjectTag tag) {
        return new FabricItem(tag);
    }

    @Override
    public Block createBlockFromTag(ObjectTag tag) {
        return new FabricBlock(tag);
    }

    @Override
    public Entity createEntityFromTag(ObjectTag tag) {
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
