package com.github.franckyi.gameadapter.forge.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.forge.common.nbt.ForgeCompoundTag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;

public class ForgeItem implements Item {
    private final net.minecraft.item.ItemStack item;
    private final CompoundTag data;

    public ForgeItem(net.minecraft.item.ItemStack item) {
        this(item, new ForgeCompoundTag(item.save(new CompoundNBT())));
    }

    public ForgeItem(CompoundTag data) {
        this(net.minecraft.item.ItemStack.of(data.get()), data);
    }

    public ForgeItem(net.minecraft.item.ItemStack item, CompoundTag data) {
        this.item = item;
        this.data = data;
    }

    @Override
    public CompoundTag getData() {
        return data;
    }

    @Override
    public boolean isBlockItem() {
        return item.getItem() instanceof BlockItem;
    }

    @Override
    public boolean isPotionItem() {
        return item.getItem() instanceof PotionItem;
    }

    @Override
    public boolean isDyeableItem() {
        return item.getItem() instanceof IDyeableArmorItem;
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.item.ItemStack get() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
