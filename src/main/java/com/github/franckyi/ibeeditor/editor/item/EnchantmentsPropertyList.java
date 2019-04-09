package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.ibeeditor.editor.PropertyList;
import com.github.franckyi.ibeeditor.editor.property.IntegerProperty;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Consumer;

public class EnchantmentsPropertyList extends PropertyList {

    private ItemStack itemStack;

    public EnchantmentsPropertyList(ItemStack itemStack) {
        this.itemStack = itemStack;
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
            this.getChildren().add(new EnchantmentProperty(itemStack, e,
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
            if (e1.isCurse()) {
                if (e2.isCurse()) {
                    return e1.getName().compareTo(e2.getName());
                } else {
                    return 1;
                }
            } else {
                if (e2.isCurse()) {
                    return -1;
                } else {
                    if (e1.canApply(itemStack)) {
                        if (e2.canApply(itemStack)) {
                            return e1.getName().compareTo(e2.getName());
                        } else {
                            return -1;
                        }
                    } else {
                        if (e2.canApply(itemStack)) {
                            return 1;
                        } else {
                            return e1.getName().compareTo(e2.getName());
                        }
                    }
                }
            }
        }
    }

    public class EnchantmentProperty extends IntegerProperty {

        protected Enchantment enchantment;

        public EnchantmentProperty(ItemStack itemStack, Enchantment enchantment, Integer initialValue, Consumer<Integer> action) {
            super(enchantment.getDisplayName(0).getUnformattedComponentText(), initialValue, action);
            this.enchantment = enchantment;
            nameLabel.setPrefWidth(COMPUTED_SIZE);
            nameLabel.setColor(enchantment.isCurse() ? TextFormatting.RED.getColor() : (enchantment.canApply(itemStack) ? TextFormatting.GREEN.getColor() : 0xffffff));
        }

        @Override
        protected void build() {
            super.build();
            this.getNode().setAlignment(Pos.RIGHT);
            this.getNode().setSpacing(5);
        }

        @Override
        protected void updateSize(int listWidth) {
            integerField.setPrefWidth(listWidth - 220);
        }
    }
}