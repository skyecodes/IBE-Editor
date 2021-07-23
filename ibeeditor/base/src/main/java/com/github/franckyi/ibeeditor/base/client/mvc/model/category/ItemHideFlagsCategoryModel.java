package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.HideFlagEntryModel;

import java.util.Locale;

public class ItemHideFlagsCategoryModel extends ItemCategoryModel {
    private int newHideFlags;

    public ItemHideFlagsCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.hide_flags", editor);
    }

    @Override
    protected void setupEntries() {
        int hideFlags = getBaseTag().getInt("HideFlags");
        for (HideFlag flag : HideFlag.values()) {
            getEntries().add(new HideFlagEntryModel(this, flag,
                    (hideFlags & flag.getValue()) > 0, value -> setFlag(flag, value)));
        }
    }

    @Override
    public void apply(CompoundTag nbt) {
        newHideFlags = 0;
        super.apply(nbt);
        if (newHideFlags > 0) {
            getNewTag().putInt("HideFlags", newHideFlags);
        } else {
            getNewTag().remove("HideFlags");
        }
    }

    private void setFlag(HideFlag flag, boolean value) {
        if (value) {
            newHideFlags += flag.getValue();
        }
    }

    public enum HideFlag {
        ENCHANTMENTS, ATTRIBUTE_MODIFIERS, UNBREAKABLE, CAN_DESTROY, CAN_PLACE_ON, OTHER, DYED;

        public String getName() {
            return "ibeeditor.gui." + name().toLowerCase(Locale.ROOT);
        }

        public int getValue() {
            return (int) Math.pow(2, ordinal());
        }
    }
}
