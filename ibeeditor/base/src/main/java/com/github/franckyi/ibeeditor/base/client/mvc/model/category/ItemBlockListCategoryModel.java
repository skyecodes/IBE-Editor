package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.IStringTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BlockSelectionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

import java.util.stream.Collectors;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemBlockListCategoryModel extends ItemCategoryModel {
    private final String tagName;
    private IListTag newBlocks;

    public ItemBlockListCategoryModel(String name, ItemEditorModel editor, String tagName) {
        super(name, editor);
        this.tagName = tagName;
    }

    @Override
    protected void setupEntries() {
        getEntries().setAll(getBaseTag().getList(tagName, ITag.STRING_ID).stream()
                .map(IStringTag.class::cast)
                .map(IStringTag::getValue)
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
    protected IText getAddListEntryButtonTooltip() {
        return translated("ibeeditor.text.block");
    }

    @Override
    public void apply(ICompoundTag nbt) {
        newBlocks = IListTag.create();
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
