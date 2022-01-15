package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.HideFlagEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;

import java.util.Arrays;
import java.util.Locale;

public class ItemHideFlagsCategoryModel extends ItemEditorCategoryModel {
    private int newHideFlags;

    public ItemHideFlagsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.HIDE_FLAGS, editor);
    }

    @Override
    protected void setupEntries() {
        int hideFlags = getTag().getInt("HideFlags");
        Arrays.stream(HideFlag.values())
                .map(flag -> new HideFlagEntryModel(this, flag, (hideFlags & flag.getValue()) > 0, value -> setFlag(flag, value)))
                .forEach(getEntries()::add);
    }

    @Override
    public void apply() {
        newHideFlags = 0;
        super.apply();
        if (newHideFlags > 0) {
            getOrCreateTag().putInt("HideFlags", newHideFlags);
        } else {
            getOrCreateTag().remove("HideFlags");
        }
    }

    private void setFlag(HideFlag flag, boolean value) {
        if (value) {
            newHideFlags += flag.getValue();
        }
    }

    public enum HideFlag {
        ENCHANTMENTS, ATTRIBUTE_MODIFIERS, UNBREAKABLE, CAN_DESTROY, CAN_PLACE_ON, OTHER, DYED;

        public MutableComponent getName() {
            return ModTexts.gui(name().toLowerCase(Locale.ROOT));
        }

        public int getValue() {
            return (int) Math.pow(2, ordinal());
        }
    }
}
