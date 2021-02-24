package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.*;
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
    @SuppressWarnings("unchecked")
    public TextFactory<?> text() {
        return FabricTextFactory.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TagFactory<?> tag() {
        return FabricTagFactory.INSTANCE;
    }

    @Override
    public ItemFactory item() {
        return FabricItem::new;
    }

    @Override
    public BlockFactory block() {
        return FabricBlock::new;
    }

    @Override
    public EntityFactory entity() {
        return FabricEntity::new;
    }

    @Override
    public Network<?> network() {
        return FabricNetwork.INSTANCE;
    }

    @Override
    public Path gameDir() {
        return FabricLoader.getInstance().getGameDir();
    }

    @Override
    public Command<ServerCommandSource> command(Function<Player, Integer> command) {
        return ctx -> command.apply(new FabricPlayer(ctx.getSource().getPlayer()));
    }
}
