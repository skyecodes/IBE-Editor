package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.util.common.text.Text;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class FabricItem implements Item {
    private String id;
    private Text name;

    public FabricItem(ItemStack item) {
        setName(FabricTextFactory.INSTANCE.from(item.getName()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ItemStack createItemStack() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", id);
        return ItemStack.fromTag(tag);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Text getName() {
        return name;
    }

    @Override
    public void setName(Text value) {
        name = value;
    }
}
