package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.impl.common.nbt.ForgeCompoundTag;
import com.github.franckyi.minecraft.impl.common.text.ForgeTextFactory;
import com.github.franckyi.minecraft.api.common.text.Text;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ForgeItem implements Item {
    private final ItemStack item;
    private final CompoundTag tag;
    private final Text name;

    public ForgeItem(ItemStack item) {
        this(item, new ForgeCompoundTag(item.write(new CompoundNBT())));
    }

    public ForgeItem(CompoundTag tag) {
        this(ItemStack.read(tag.get()), tag);
    }

    public ForgeItem(ItemStack item, CompoundTag tag) {
        this.item = item;
        this.tag = tag;
        name = ForgeTextFactory.INSTANCE.fromComponent(item.getDisplayName());
    }

    @Override
    public CompoundTag getTag() {
        return tag;
    }

    @Override
    public Text getName() {
        return name;
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
