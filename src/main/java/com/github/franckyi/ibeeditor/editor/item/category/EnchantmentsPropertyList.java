package com.github.franckyi.ibeeditor.editor.item.category;

import com.github.franckyi.ibeeditor.editor.item.property.EnchantmentProperty;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class EnchantmentsPropertyList extends ItemEditorPropertyList {

    public EnchantmentsPropertyList(ItemStack itemStack) {
        super(itemStack);
        NBTTagList enchTag = itemStack.getEnchantmentTagList();
        Map<Enchantment, Integer> itemEnch = new HashMap<>(enchTag.size());
        for (int i = 0; i < enchTag.size(); i++) {
            NBTTagCompound c = enchTag.getCompound(i);
            String id = c.getString("id");
            int level = c.getInt("lvl");
            itemEnch.put(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(id)), level);
        }
        List<Enchantment> enchantments = new ArrayList<>(ForgeRegistries.ENCHANTMENTS.getValues());
        enchantments.sort(new EnchantmentComparator());
        for (Enchantment e : enchantments) {
            this.getChildren().add(new EnchantmentProperty(e,
                    itemEnch.getOrDefault(e, 0), i -> this.setEnchantment(e, i)));
        }
    }

    private void setEnchantment(Enchantment ench, int value) {
        if (value > 0) {
            itemStack.addEnchantment(ench, value);
        }
    }

    @Override
    public void apply() {
        itemStack.getOrCreateTag().remove("Enchantments");
        super.apply();
    }

    private class EnchantmentComparator implements Comparator<Enchantment> {

        @Override
        public int compare(Enchantment e1, Enchantment e2) {
            if (e1.canApply(itemStack)) {
                if (e2.canApply(itemStack)) {
                    if (e1.isCurse()) {
                        if (e2.isCurse()) {
                            return 0;
                        } else {
                            return -1;
                        }
                    } else {
                        if (e2.isCurse()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                } else {
                    return 1;
                }
            } else {
                if (e2.canApply(itemStack)) {
                    return -1;
                } else {
                    if (e1.isCurse()) {
                        if (e2.isCurse()) {
                            return 0;
                        } else {
                            return -1;
                        }
                    } else {
                        if (e2.isCurse()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
    }
}
