package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.StringTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BlockSelectionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

import java.util.stream.Collectors;

public class ItemBlockListCategoryModel extends ItemCategoryModel {
    private final String tagName;
    private ListTag newBlocks;

    public ItemBlockListCategoryModel(String name, ItemEditorModel editor, String tagName) {
        super(name, editor);
        this.tagName = tagName;
    }

    @Override
    protected void setupEntries() {
        getEntries().setAll(getBaseTag().getList(tagName, Tag.STRING_ID).stream()
                .map(StringTag.class::cast)
                .map(StringTag::getValue)
                .map(this::createBlockEntry)
                .collect(Collectors.toList()));
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    public EntryModel createListEntry() {
        return createBlockEntry("");
    }

    private EntryModel createBlockEntry(String id) {
        return new BlockSelectionEntryModel(this, null, id, this::addBlock);
    }

    @Override
    public void apply(CompoundTag nbt) {
        newBlocks = ListTag.create();
        super.apply(nbt);
        if (!newBlocks.isEmpty()) {
            getNewTag().putTag(tagName, newBlocks);
        } else {
            getNewTag().remove(tagName);
        }
    }

    private void addBlock(String id) {
        newBlocks.addString(id);
    }
}
