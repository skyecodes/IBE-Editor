package com.github.franckyi.ibeeditor.editor.item.category;

import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;
import com.github.franckyi.ibeeditor.editor.item.property.LoreProperty;
import com.github.franckyi.ibeeditor.editor.property.FormattedTextProperty;
import com.github.franckyi.ibeeditor.node.TexturedButton;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

import java.util.Collections;
import java.util.List;

public class DisplayPropertyList extends ItemEditorPropertyList {

    public DisplayPropertyList(ItemStack itemStack) {
        super(itemStack);
        this.addAll(
                new FormattedTextProperty("Name", itemStack.getDisplayName().getFormattedText(),
                        s -> itemStack.setDisplayName(new TextComponentString(s))),
                new AddButton()
        );
        NBTTagCompound displayTag = itemStack.getOrCreateChildTag("display");
        if (!displayTag.contains("Lore", Constants.NBT.TAG_LIST)) {
            displayTag.put("Lore", new NBTTagList());
        }
        NBTTagList loreTag = displayTag.getList("Lore", Constants.NBT.TAG_STRING);
        for (int i = 0; i < loreTag.size(); i++) {
            this.addLore(loreTag.getString(i));
        }
        for (int i = 0; i < this.getLoreCount(); i++) {
            this.getLoreProperty(i).update(i);
        }
    }

    @Override
    public void apply() {
        itemStack.getOrCreateChildTag("display").put("Lore", new NBTTagList());
        super.apply();
    }

    private void addLore(String lore) {
        int index = this.getChildren().size() - 2;
        this.getChildren().add(index + 1, new LoreProperty(this, index, lore, s -> this.setLore(index, s)));
    }

    private void setLore(int index, String lore) {
        NBTTagList list = itemStack.getOrCreateChildTag("display")
                .getList("Lore", Constants.NBT.TAG_STRING);
        NBTTagString loreTag = new NBTTagString(lore);
        if (index >= list.size()) {
            list.add(loreTag);
        } else {
            list.set(index, loreTag);
        }
    }

    private LoreProperty getLoreProperty(int index) {
        return (LoreProperty) this.getChildren().get(index + 1);
    }

    public void swapLores(int i1, int i2) {
        if (i1 >= 0 && i2 < this.getLoreCount()) {
            List<? extends AbstractProperty> list = this.getChildren();
            Collections.swap(list, i1 + 1, i2 + 1);
            this.getLoreProperty(i1).update(i1);
            this.getLoreProperty(i2).update(i2);
        }
    }

    public void removeLore(int index) {
        this.getChildren().remove(index + 1);
        for (int i = 0; i < this.getLoreCount(); i++) {
            this.getLoreProperty(i).update(i);
        }
    }

    public int getLoreCount() {
        return this.getChildren().size() - 2;
    }

    private class AddButton extends AbstractProperty<Void, VBox> {
        private AddButton() {
            super("", null, aVoid -> {
            }, new VBox());
            TexturedButton b = new TexturedButton("add.png");
            b.getOnMouseClickedListeners().add(event -> {
                addLore("");
                int count = getLoreCount();
                getLoreProperty(--count).update(count--);
                getLoreProperty(count).update(count);
            });
            this.getNode().getChildren().add(b);
            this.getNode().setAlignment(Pos.CENTER);
        }

        @Override
        public Void getValue() {
            return null;
        }
    }

}
