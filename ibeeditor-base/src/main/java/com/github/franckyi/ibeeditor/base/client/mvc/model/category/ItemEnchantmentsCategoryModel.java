package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnchantmentEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.HashMap;
import java.util.Map;

public class ItemEnchantmentsCategoryModel extends ItemCategoryModel {
    private Map<String, Integer> baseEnchMap;
    private IListTag newEnch;

    public ItemEnchantmentsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ENCHANTMENTS, editor);
    }

    @Override
    protected void setupEntries() {
        IListTag enchantments = getBaseTag().getList("Enchantments", ITag.COMPOUND_ID);
        baseEnchMap = new HashMap<>(enchantments.size());
        for (int i = 0; i < enchantments.size(); i++) {
            ICompoundTag compound = enchantments.getCompound(i);
            baseEnchMap.put(compound.getString("id"), compound.getInt("lvl"));
        }
        RegistryHandler.get().getEnchantmentRegistry().getEntries()
                .stream()
                .sorted((e0, e1) -> sortEnchantments(e0.getValue(), e1.getValue()))
                .forEachOrdered(e -> addBaseEnchantment(e.getKey().getId(), e.getValue()));
    }

    private void addBaseEnchantment(IIdentifier id, IEnchantment enchantment) {
        getEntries().add(new EnchantmentEntryModel(this, enchantment, enchantment.canApply(getEditor().getTarget()),
                baseEnchMap.getOrDefault(id.toString(), 0), lvl -> addNewEnchantment(id.toString(), lvl)));
    }

    @Override
    public void apply(ICompoundTag nbt) {
        newEnch = IListTag.create();
        super.apply(nbt);
        if (!newEnch.isEmpty()) {
            getNewTag().putTag("Enchantments", newEnch);
        }
    }

    private void addNewEnchantment(String id, int lvl) {
        if (lvl != 0) {
            ICompoundTag tag = ICompoundTag.create();
            tag.putString("id", id);
            tag.putInt("lvl", lvl);
            newEnch.addTag(tag);
        }
    }

    private int sortEnchantments(IEnchantment e1, IEnchantment e2) {
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
