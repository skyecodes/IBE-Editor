package com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.ibeeditor.base.client.mvc.base.model.entry.BooleanEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model.ItemEditorModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemHideFlagsEditorCategoryModel extends AbstractItemEditorCategoryModel {
    private int newHideFlags;

    public ItemHideFlagsEditorCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.editor.category.hide_flags", editor);
    }

    @Override
    protected void setupEntries() {
        int hideFlags = getBaseTag().getInt("HideFlags");
        for (HideFlag flag : HideFlag.values()) {
            getEntries().add(new BooleanEditorEntryModel(this, translated(flag.getName()),
                    (hideFlags & flag.getValue()) > 0, value -> setFlag(flag, value)).withLabelWeight(3));
        }
    }

    @Override
    public void apply(CompoundTag nbt) {
        newHideFlags = 0;
        super.apply(nbt);
        if (newHideFlags > 0) {
            getNewTag().putInt("HideFlags", newHideFlags);
        }
    }

    private void setFlag(HideFlag flag, boolean value) {
        if (value) {
            newHideFlags += flag.getValue();
        }
    }

    private enum HideFlag {
        ENCHANTMENTS, ATTRIBUTE_MODIFIERS, UNBREAKABLE, CAN_DESTROY, CAN_PLACE_ON, OTHER, DYED;

        public String getName() {
            return "ibeeditor.gui.editor.item.entry.hide_" + name().toLowerCase();
        }

        public int getValue() {
            return (int) Math.pow(2, ordinal());
        }
    }
}
