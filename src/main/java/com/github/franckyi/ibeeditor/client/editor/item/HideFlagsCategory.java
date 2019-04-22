package com.github.franckyi.ibeeditor.client.editor.item;

import com.github.franckyi.ibeeditor.client.editor.Category;
import com.github.franckyi.ibeeditor.client.editor.property.PropertyBoolean;
import net.minecraft.item.ItemStack;

public class HideFlagsCategory extends Category {

    private final ItemStack itemStack;
    private int hideFlags;

    public HideFlagsCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.hideFlags = itemStack.getOrCreateTag().getInt("HideFlags");
        this.addAll(
                new PropertyBoolean("Hide Enchantments",
                        this.hasHideFlag(5), b -> this.addHideFlags(b ? 1 : 0)),
                new PropertyBoolean("Hide Attribute modifiers",
                        this.hasHideFlag(4), b -> this.addHideFlags(b ? 2 : 0)),
                new PropertyBoolean("Hide Unbreakable",
                        this.hasHideFlag(3), b -> this.addHideFlags(b ? 4 : 0)),
                new PropertyBoolean("Hide 'Can destroy'",
                        this.hasHideFlag(2), b -> this.addHideFlags(b ? 8 : 0)),
                new PropertyBoolean("Hide 'Can place on'",
                        this.hasHideFlag(1), b -> this.addHideFlags(b ? 16 : 0)),
                new PropertyBoolean("Hide Potion effects & shield pattern info",
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
        itemStack.getOrCreateTag().remove("HideFlags");
        hideFlags = 0;
        super.apply();
        if (hideFlags != 0) {
            itemStack.getOrCreateTag().putInt("HideFlags", hideFlags);
        }
    }
}
