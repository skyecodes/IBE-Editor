package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.*;
import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public final class ForgeCommonHooks implements CommonHooks {
    public static final CommonHooks INSTANCE = new ForgeCommonHooks();

    private ForgeCommonHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextFactory<?> text() {
        return ForgeTextFactory.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TagFactory<?> tag() {
        return ForgeTagFactory.INSTANCE;
    }

    @Override
    public ItemFactory item() {
        return ForgeItem::new;
    }

    @Override
    public BlockFactory block() {
        return ForgeBlock::new;
    }

    @Override
    public EntityFactory entity() {
        return ForgeEntity::new;
    }

    @Override
    public Network<?> network() {
        return ForgeNetwork.INSTANCE;
    }

    @Override
    public Path gameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public Command<CommandSource> command(Function<Player, Integer> command) {
        return ctx -> command.apply(new ForgePlayer(ctx.getSource().asPlayer()));
    }
}
