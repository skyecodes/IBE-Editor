package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnchantmentEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import com.github.franckyi.ibeeditor.base.common.TagHelper;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class ItemEnchantmentsCategoryModel extends ItemCategoryModel {
    private Map<ResourceLocation, Integer> baseEnchMap;
    private ListTag newEnch;

    public ItemEnchantmentsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ENCHANTMENTS, editor);
    }

    @Override
    protected void setupEntries() {
        ListTag enchantments = getBaseTag().getList("Enchantments", TagHelper.COMPOUND_ID);
        baseEnchMap = new HashMap<>(enchantments.size());
        for (int i = 0; i < enchantments.size(); i++) {
            CompoundTag compound = enchantments.getCompound(i);
            baseEnchMap.put(new ResourceLocation(compound.getString("id")), compound.getInt("lvl"));
        }
        Registry.ENCHANTMENT.entrySet().stream()
                .sorted((e0, e1) -> sortEnchantments(e0.getValue(), e1.getValue()))
                .forEachOrdered(e -> addBaseEnchantment(e.getKey().location(), e.getValue()));
    }

    private void addBaseEnchantment(ResourceLocation id, Enchantment enchantment) {
        getEntries().add(new EnchantmentEntryModel(this, enchantment, enchantment.canEnchant(getEditor().getTarget()),
                baseEnchMap.getOrDefault(id, 0), lvl -> addNewEnchantment(id.toString(), lvl)));
    }

    @Override
    public void apply(CompoundTag nbt) {
        newEnch = new ListTag();
        super.apply(nbt);
        if (!newEnch.isEmpty()) {
            getNewTag().put("Enchantments", newEnch);
        } else {
            getNewTag().remove("Enchantments");
        }
    }

    private void addNewEnchantment(String id, int lvl) {
        if (lvl != 0) {
            CompoundTag tag = new CompoundTag();
            tag.putString("id", id);
            tag.putInt("lvl", lvl);
            newEnch.add(tag);
        }
    }

    private int sortEnchantments(Enchantment e1, Enchantment e2) {
        if (e1.isCurse()) {
            if (e2.isCurse()) {
                return e1.getDescriptionId().compareTo(e2.getDescriptionId());
            } else {
                return 1;
            }
        } else {
            if (e2.isCurse()) {
                return -1;
            } else {
                if (e1.canEnchant(getEditor().getTarget())) {
                    if (e2.canEnchant(getEditor().getTarget())) {
                        return e1.getDescriptionId().compareTo(e2.getDescriptionId());
                    } else {
                        return -1;
                    }
                } else {
                    if (e2.canEnchant(getEditor().getTarget())) {
                        return 1;
                    } else {
                        return e1.getDescriptionId().compareTo(e2.getDescriptionId());
                    }
                }
            }
        }
    }
}
