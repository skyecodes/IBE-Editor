package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.registry.Enchantment;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnchantmentEntryModel;

import java.util.HashMap;
import java.util.Map;

public class ItemEnchantmentsCategoryModel extends ItemCategoryModel {
    private Map<String, Integer> baseEnchMap;
    private ListTag newEnch;

    public ItemEnchantmentsCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.enchantments", editor);
    }

    @Override
    protected void setupEntries() {
        ListTag enchantments = getBaseTag().getList("Enchantments", Tag.COMPOUND_ID);
        baseEnchMap = new HashMap<>(enchantments.size());
        for (int i = 0; i < enchantments.size(); i++) {
            CompoundTag compound = enchantments.getCompound(i);
            baseEnchMap.put(compound.getString("id"), compound.getInt("lvl"));
        }
        Game.getCommon().getRegistries().getEnchantments()
                .stream()
                .sorted(this::sortEnchantments)
                .forEachOrdered(this::addBaseEnchantment);
    }

    private void addBaseEnchantment(Enchantment enchantment) {
        getEntries().add(new EnchantmentEntryModel(this, enchantment, enchantment.canApply(getEditor().getTarget()),
                baseEnchMap.getOrDefault(enchantment.getId(), 0), lvl -> addNewEnchantment(enchantment.getId(), lvl)));
    }

    @Override
    public void apply(CompoundTag nbt) {
        newEnch = Game.getCommon().getTagFactory().createListTag();
        super.apply(nbt);
        if (!newEnch.isEmpty()) {
            getNewTag().putTag("Enchantments", newEnch);
        }
    }

    private void addNewEnchantment(String id, int lvl) {
        if (lvl != 0) {
            CompoundTag tag = Game.getCommon().getTagFactory().createCompoundTag();
            tag.putString("id", id);
            tag.putInt("lvl", lvl);
            newEnch.addTag(tag);
        }
    }

    private int sortEnchantments(Enchantment e1, Enchantment e2) {
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
                if (e1.canApply(getEditor().getTarget())) {
                    if (e2.canApply(getEditor().getTarget())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return -1;
                    }
                } else {
                    if (e2.canApply(getEditor().getTarget())) {
                        return 1;
                    } else {
                        return e1.getName().compareTo(e2.getName());
                    }
                }
            }
        }
    }
}
