package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.BlockSelectionEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ItemBlockListCategoryModel extends ItemEditorCategoryModel {
    private final String tagName;
    private ListTag newBlocks;

    public ItemBlockListCategoryModel(Component name, ItemEditorModel editor, String tagName) {
        super(name, editor);
        this.tagName = tagName;
    }

    @Override
    protected void setupEntries() {
        getTag().getList(tagName, Tag.TAG_STRING).stream()
                .map(Tag::getAsString)
                .map(this::createBlockEntry)
                .forEach(getEntries()::add);
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
    public void apply() {
        newBlocks = new ListTag();
        super.apply();
        if (!newBlocks.isEmpty()) {
            getOrCreateTag().put(tagName, newBlocks);
        } else if (getOrCreateTag().contains(tagName)) {
            getOrCreateTag().remove(tagName);
        }
    }

    private void addBlock(String id) {
        newBlocks.add(StringTag.valueOf(id));
    }
}
