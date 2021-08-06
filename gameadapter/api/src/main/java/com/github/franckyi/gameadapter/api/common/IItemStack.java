package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;

public interface IItemStack {
    static IItemStack fromTag(ICompoundTag data) {
        return Game.getCommon().createItemFromTag(data);
    }

    static IItemStack fromId(IIdentifier id) {
        return Game.getCommon().createItemFromId(id);
    }

    static IItemStack fromPotion(IIdentifier id, int color) {
        ICompoundTag data = ICompoundTag.create();
        ICompoundTag tag = ICompoundTag.create();
        tag.putString("Potion", id.toString());
        if (color != Color.NONE) {
            tag.putInt("CustomPotionColor", color);
        }
        data.putString("id", "minecraft:potion");
        data.putInt("Count", 1);
        data.putTag("tag", tag);
        return fromTag(data);
    }

    static IItemStack fromArmor(ICompoundTag data, int color) {
        if (color == Color.NONE) {
            data.getCompound("tag").remove("display");
        } else {
            if (!data.contains("tag", ITag.COMPOUND_ID)) {
                data.put("tag", ICompoundTag.create());
            }
            ICompoundTag tag = data.getCompound("tag");
            if (!tag.contains("display", ITag.COMPOUND_ID)) {
                tag.put("display", ICompoundTag.create());
            }
            ICompoundTag display = tag.getCompound("display");
            display.putInt("color", color);
        }
        return fromTag(data);
    }

    boolean isEmpty();

    ICompoundTag getData();

    boolean isBlockItem();

    boolean isPotionItem();

    boolean isDyeableItem();
}
