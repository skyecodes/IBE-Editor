package com.github.franckyi.gameadapter.fabric;

import com.github.franckyi.gameadapter.api.GameCommon;
import com.github.franckyi.gameadapter.api.common.Registries;
import com.github.franckyi.gameadapter.api.common.network.Network;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.TagFactory;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.github.franckyi.gameadapter.fabric.common.FabricRegistries;
import com.github.franckyi.gameadapter.fabric.common.nbt.FabricTagFactory;
import com.github.franckyi.gameadapter.fabric.common.network.FabricNetwork;
import com.github.franckyi.gameadapter.fabric.common.text.FabricTextComponentFactory;
import com.github.franckyi.gameadapter.fabric.common.world.FabricBlock;
import com.github.franckyi.gameadapter.fabric.common.world.FabricEntity;
import com.github.franckyi.gameadapter.fabric.common.world.FabricItem;
import com.github.franckyi.gameadapter.fabric.common.world.FabricPlayer;
import com.mojang.brigadier.Command;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;
import java.util.function.Function;

public final class FabricGameCommon implements GameCommon {
    public static final GameCommon INSTANCE = new FabricGameCommon();

    private FabricGameCommon() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextComponentFactory<Text> getTextComponentFactory() {
        return FabricTextComponentFactory.INSTANCE;
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
    public Item createItem(String id) {
        return new FabricItem(new ItemStack(Registry.ITEM.get(Identifier.tryParse(id))));
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

    @Override
    public Registries getRegistries() {
        return FabricRegistries.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PlayerFactory<PlayerEntity> getPlayerFactory() {
        return FabricPlayer::new;
    }

    @Override
    public String translate(String key) {
        return Language.getInstance().get(key);
    }
}
