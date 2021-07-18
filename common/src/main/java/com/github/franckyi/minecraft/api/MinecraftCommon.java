package com.github.franckyi.minecraft.api;

import com.github.franckyi.minecraft.api.common.Registries;
import com.github.franckyi.minecraft.api.common.network.Network;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.TagFactory;
import com.github.franckyi.minecraft.api.common.text.TextFactory;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.mojang.brigadier.Command;

import java.nio.file.Path;
import java.util.function.Function;

public interface MinecraftCommon {
    <T> TextFactory<T> getTextFactory();

    TagFactory getTagFactory();

    Item createItem(CompoundTag tag);

    Block createBlock(CompoundTag state, CompoundTag data);

    Entity createEntity(CompoundTag tag);

    Network getNetwork();

    Path getGameDir();

    Command<?> createCommand(Function<Player, Integer> command);

    Registries getRegistries();
}
