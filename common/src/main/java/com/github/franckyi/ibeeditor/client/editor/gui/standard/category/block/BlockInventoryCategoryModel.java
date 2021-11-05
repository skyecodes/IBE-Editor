package com.github.franckyi.ibeeditor.client.editor.gui.standard.category.block;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.BlockEditorModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.BlockCategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.InventoryItemEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.TagHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public class BlockInventoryCategoryModel extends BlockCategoryModel {
    private ListTag newItems;

    public BlockInventoryCategoryModel(BlockEditorModel editor) {
        super(ModTexts.INVENTORY, editor);
    }

    @Override
    protected void setupEntries() {
        ListTag items = getBaseTag().getList("Items", TagHelper.COMPOUND_ID);
        for (Tag tag : items) {
            CompoundTag itemTag = (CompoundTag) tag;
            byte slot = itemTag.getByte("Slot");
            ItemStack item = ItemStack.of(itemTag);
            getEntries().add(createListEntry(item, slot));
        }
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.ITEM;
    }

    @Override
    public EntryModel createListEntry() {
        return createListEntry(new ItemStack(Items.AIR), (byte) 0);
    }

    private EntryModel createListEntry(ItemStack item, byte slot) {
        return new InventoryItemEntryModel(this, item, slot, this::addItem);
    }

    private void addItem(ItemStack item, byte slot) {
        CompoundTag tag = item.save(new CompoundTag());
        tag.putByte("Slot", slot);
        newItems.add(tag);
    }

    @Override
    public BlockState apply(BlockState state, CompoundTag tag) {
        newItems = new ListTag();
        BlockState s = super.apply(state, tag);
        tag.put("Items", newItems);
        return s;
    }
}
