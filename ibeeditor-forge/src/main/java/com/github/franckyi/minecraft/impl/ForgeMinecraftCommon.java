package com.github.franckyi.minecraft.impl;

import com.github.franckyi.minecraft.api.MinecraftCommon;
import com.github.franckyi.minecraft.api.common.Registries;
import com.github.franckyi.minecraft.api.common.network.Network;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.TagFactory;
import com.github.franckyi.minecraft.api.common.text.TextComponentFactory;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.impl.common.ForgeRegistries;
import com.github.franckyi.minecraft.impl.common.nbt.ForgeTagFactory;
import com.github.franckyi.minecraft.impl.common.network.ForgeNetwork;
import com.github.franckyi.minecraft.impl.common.text.ForgeTextComponentFactory;
import com.github.franckyi.minecraft.impl.common.world.ForgeBlock;
import com.github.franckyi.minecraft.impl.common.world.ForgeEntity;
import com.github.franckyi.minecraft.impl.common.world.ForgeItem;
import com.github.franckyi.minecraft.impl.common.world.ForgePlayer;
import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public final class ForgeMinecraftCommon implements MinecraftCommon {
    public static final MinecraftCommon INSTANCE = new ForgeMinecraftCommon();

    private ForgeMinecraftCommon() {
    }

    @Override
    public TextComponentFactory getTextComponentFactory() {
        return ForgeTextComponentFactory.INSTANCE;
    }

    @Override
    public TagFactory getTagFactory() {
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
    public Command<CommandSource> createCommand(Function<Player, Integer> command) {
        return ctx -> command.apply(new ForgePlayer(ctx.getSource().getPlayerOrException()));
    }

    @Override
    public Registries getRegistries() {
        return ForgeRegistries.INSTANCE;
    }
}
