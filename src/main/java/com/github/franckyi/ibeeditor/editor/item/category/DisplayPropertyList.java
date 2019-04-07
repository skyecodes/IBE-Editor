package com.github.franckyi.ibeeditor.editor.item.category;

import com.github.franckyi.ibeeditor.editor.AbstractProperty;
import com.github.franckyi.ibeeditor.editor.EditablePropertyList;
import com.github.franckyi.ibeeditor.editor.item.property.LoreProperty;
import com.github.franckyi.ibeeditor.editor.property.FormattedTextProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

public class DisplayPropertyList extends EditablePropertyList<String> {

    private ItemStack itemStack;

    public DisplayPropertyList(ItemStack itemStack) {
        super(1);
        this.itemStack = itemStack;
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
            this.addProperty(loreTag.getString(i));
        }
        for (int i = 0; i < this.getPropertyCount(); i++) {
            this.getProperty(i).update(i);
        }
    }

    @Override
    public void apply() {
        itemStack.getOrCreateChildTag("display").put("Lore", new NBTTagList());
        super.apply();
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

    @Override
    protected AbstractProperty<String, ?> createNewProperty(String initialValue, int index) {
        return new LoreProperty(this, index, initialValue, s -> setLore(index, s));
    }

    @Override
    protected String getDefaultPropertyValue() {
        return "";
    }
}
