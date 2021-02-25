package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.*;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
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
    public TextFactory text() {
        return ForgeTextFactory.INSTANCE;
    }

    @Override
    public Item createItemFromTag(ObjectTag tag) {
        return new ForgeItem(tag);
    }

    @Override
    public Block createBlockFromTag(ObjectTag tag) {
        return new ForgeBlock(tag);
    }

    @Override
    public Entity createEntityFromTag(ObjectTag tag) {
        return new ForgeEntity(tag);
    }

    @Override
    public Network getNetwork() {
        return ForgeNetwork.INSTANCE;
    }

    @Override
    public Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public Command<CommandSource> command(Function<Player, Integer> command) {
        return ctx -> command.apply(new ForgePlayer(ctx.getSource().asPlayer()));
    }
}
