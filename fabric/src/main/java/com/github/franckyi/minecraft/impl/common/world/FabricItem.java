package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.impl.common.nbt.FabricCompoundTag;
import com.github.franckyi.minecraft.impl.common.text.FabricTextFactory;
import net.minecraft.item.ItemStack;

public class FabricItem implements Item {
    private final ItemStack item;
    private final CompoundTag tag;
    private Text name;
    private Text defaultName;

    public FabricItem(ItemStack item) {
        this(item, new FabricCompoundTag(item.toTag(new net.minecraft.nbt.CompoundTag())));
    }

    public FabricItem(CompoundTag tag) {
        this(ItemStack.fromTag(tag.get()), tag);
    }

    private FabricItem(ItemStack item, CompoundTag tag) {
        this.item = item;
        this.tag = tag;
    }

    @Override
    public CompoundTag getTag() {
        return tag;
    }

    @Override
    public Text getName() {
        if (name == null) {
            name = FabricTextFactory.INSTANCE.createTextFromComponent(item.getName());
        }
        return name;
    }

    @Override
    public Text getDefaultName() {
        if (defaultName == null) {
            defaultName = FabricTextFactory.INSTANCE.createTextFromComponent(item.getItem().getName());
        }
        return defaultName;
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
