package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.BlockSelectionEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ItemBlockListCategoryModel extends ItemCategoryModel {
    private final String tagName;
    private ListTag newBlocks;

    public ItemBlockListCategoryModel(Component name, ItemEditorModel editor, String tagName) {
        super(name, editor);
        this.tagName = tagName;
    }

    @Override
    protected void setupEntries() {
        getEntries().setAll(getBaseTag().getList(tagName, Tag.TAG_STRING).stream()
                .map(StringTag.class::cast)
                .map(StringTag::getAsString)
                .map(this::createBlockEntry)
                .toList());
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    public EntryModel createNewListEntry() {
        return createBlockEntry("");
    }

    private EntryModel createBlockEntry(String id) {
        return new BlockSelectionEntryModel(this, null, id, this::addBlock);
    }

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.BLOCK;
    }

    @Override
    public void apply(CompoundTag nbt) {
        newBlocks = new ListTag();
        super.apply(nbt);
        if (!newBlocks.isEmpty()) {
            getNewTag().put(tagName, newBlocks);
        } else {
            getNewTag().remove(tagName);
        }
    }

    private void addBlock(String id) {
        newBlocks.add(StringTag.valueOf(id));
    }
}
