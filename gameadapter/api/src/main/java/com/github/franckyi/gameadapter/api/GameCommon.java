package com.github.franckyi.gameadapter.api;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.Registries;
import com.github.franckyi.gameadapter.api.common.network.Network;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.gameadapter.api.common.tag.TagFactory;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.mojang.brigadier.Command;

import java.nio.file.Path;
import java.util.function.Function;

public interface GameCommon {
    <T> TextComponentFactory<T> getTextComponentFactory();

    TagFactory getTagFactory();

    Item createItem(CompoundTag data);

    Item createItem(String id);

    Block createBlock(CompoundTag state, CompoundTag data);

    Entity createEntity(CompoundTag data);

    Network getNetwork();

    Path getGameDir();

    Command<?> createCommand(Function<Player, Integer> command);

    Registries getRegistries();

    <P> PlayerFactory<P> getPlayerFactory();

    String translate(String translationKey);

    interface PlayerFactory<P> {
        Player createPlayer(P player);
    }

    default Item createPotionItem(String potionId, int color) {
        CompoundTag data = CompoundTag.create();
        CompoundTag tag = CompoundTag.create();
        tag.putString("Potion", potionId);
        if (color != Color.NONE) {
            tag.putInt("CustomPotionColor", color);
        }
        data.putString("id", "minecraft:potion");
        data.putInt("Count", 1);
        data.putTag("tag", tag);
        return createItem(data);
    }

    default Item createArmorItem(CompoundTag data, int color) {
        if (color == Color.NONE) {
            data.getCompound("tag").remove("display");
        } else {
            if (!data.contains("tag", Tag.COMPOUND_ID)) {
                data.put("tag", CompoundTag.create());
            }
            CompoundTag tag = data.getCompound("tag");
            if (!tag.contains("display", Tag.COMPOUND_ID)) {
                tag.put("display", CompoundTag.create());
            }
            CompoundTag display = tag.getCompound("display");
            display.putInt("color", color);
        }
        return createItem(data);
    }
}
