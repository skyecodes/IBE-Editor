package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.impl.common.*;
import com.github.franckyi.gamehooks.impl.common.tag.ForgeTagFactory;
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
    public TextFactory textFactory() {
        return ForgeTextFactory.INSTANCE;
    }

    @Override
    public TagFactory tagFactory() {
        return ForgeTagFactory.INSTANCE;
    }

    @Override
    public Item createItem(CompoundTag tag) {
        return new ForgeItem(tag);
    }

    @Override
    public Block createBlock(CompoundTag state, CompoundTag data) {
        return new ForgeBlock(state, data);
    }

    @Override
    public Entity createEntity(CompoundTag tag) {
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
