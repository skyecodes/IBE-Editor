package com.github.franckyi.gameadapter.forge;

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
import com.github.franckyi.gameadapter.forge.common.ForgeRegistries;
import com.github.franckyi.gameadapter.forge.common.nbt.ForgeTagFactory;
import com.github.franckyi.gameadapter.forge.common.network.ForgeNetwork;
import com.github.franckyi.gameadapter.forge.common.text.ForgeTextComponentFactory;
import com.github.franckyi.gameadapter.forge.common.world.ForgeBlock;
import com.github.franckyi.gameadapter.forge.common.world.ForgeEntity;
import com.github.franckyi.gameadapter.forge.common.world.ForgeItem;
import com.github.franckyi.gameadapter.forge.common.world.ForgePlayer;
import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public final class ForgeGameCommon implements GameCommon {
    public static final GameCommon INSTANCE = new ForgeGameCommon();

    private ForgeGameCommon() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextComponentFactory<ITextComponent> getTextComponentFactory() {
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
    public Item createItem(String id) {
        return new ForgeItem(new ItemStack(net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(id))));
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

    @Override
    @SuppressWarnings("unchecked")
    public PlayerFactory<PlayerEntity> getPlayerFactory() {
        return ForgePlayer::new;
    }

    @Override
    public String translate(String translationKey) {
        return LanguageMap.getInstance().getOrDefault(translationKey);
    }
}
