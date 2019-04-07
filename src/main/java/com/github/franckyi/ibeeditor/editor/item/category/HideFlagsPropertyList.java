package com.github.franckyi.ibeeditor.editor.item.category;

import com.github.franckyi.ibeeditor.editor.property.BooleanProperty;
import net.minecraft.item.ItemStack;

public class HideFlagsPropertyList extends ItemEditorPropertyList {

    private int hideFlags;

    public HideFlagsPropertyList(ItemStack itemStack) {
        super(itemStack);
        hideFlags = itemStack.getOrCreateTag().getInt("HideFlags");
        this.addAll(
                new BooleanProperty("Hide Enchantments",
                        this.hasHideFlag(5), b -> this.addHideFlags(b ? 1 : 0)),
                new BooleanProperty("Hide Attribute modifiers",
                        this.hasHideFlag(4), b -> this.addHideFlags(b ? 2 : 0)),
                new BooleanProperty("Hide Unbreakable",
                        this.hasHideFlag(3), b -> this.addHideFlags(b ? 4 : 0)),
                new BooleanProperty("Hide 'Can destroy'",
                        this.hasHideFlag(2), b -> this.addHideFlags(b ? 8 : 0)),
                new BooleanProperty("Hide 'Can place on'",
                        this.hasHideFlag(1), b -> this.addHideFlags(b ? 16 : 0)),
                new BooleanProperty("Hide Potion effects & shield pattern info",
                        this.hasHideFlag(0), b -> this.addHideFlags(b ? 32 : 0))
        );
    }

    private void addHideFlags(int i) {
        hideFlags += i;
    }

    private boolean hasHideFlag(int i) {
        return String.format("%6s", Integer.toBinaryString(hideFlags)).replace(" ", "0").charAt(i) == '1';
    }

    @Override
    public void apply() {
        hideFlags = 0;
        super.apply();
        itemStack.getOrCreateTag().putInt("HideFlags", hideFlags);
    }
}
