package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnchantmentEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.stream.Collectors;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemEnchantmentsCategoryModel extends ItemCategoryModel {
    private IListTag newEnch;

    public ItemEnchantmentsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ENCHANTMENTS, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(getBaseTag().getList("Enchantments", ITag.COMPOUND_ID)
                .stream()
                .map(ICompoundTag.class::cast)
                .map(this::createEnchantment)
                .collect(Collectors.toList()));
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    protected IText getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.enchantment");
    }

    @Override
    public EntryModel createListEntry() {
        return createEnchantment("", 0);
    }

    private EnchantmentEntryModel createEnchantment(ICompoundTag tag) {
        return createEnchantment(tag.getString("id"), tag.getInt("lvl"));
    }

    private EnchantmentEntryModel createEnchantment(String id, int level) {
        return new EnchantmentEntryModel(this, id, level, this::addEnchantment);
    }

    private void addEnchantment(String id, int lvl) {
        ICompoundTag tag = ICompoundTag.create();
        tag.putString("id", id);
        tag.putInt("lvl", lvl);
        newEnch.addTag(tag);
    }

    @Override
    public void apply(ICompoundTag nbt) {
        newEnch = IListTag.create();
        super.apply(nbt);
        if (!newEnch.isEmpty()) {
            getNewTag().putTag("Enchantments", newEnch);
        }
    }
}
