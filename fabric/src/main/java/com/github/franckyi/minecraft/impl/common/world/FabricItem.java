package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.impl.common.nbt.FabricCompoundTag;
import com.github.franckyi.minecraft.impl.common.text.FabricTextFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class FabricItem implements Item {
    private final ItemStack item;
    private final CompoundTag tag;

    public FabricItem(ItemStack item) {
        this(item, new FabricCompoundTag(item.writeNbt(new NbtCompound())));
    }

    public FabricItem(CompoundTag tag) {
        this(ItemStack.fromNbt(tag.get()), tag);
    }

    private FabricItem(ItemStack item, CompoundTag tag) {
        this.item = item;
        this.tag = tag;
    }

    @Override
    public CompoundTag getData() {
        return tag;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ItemStack get() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
