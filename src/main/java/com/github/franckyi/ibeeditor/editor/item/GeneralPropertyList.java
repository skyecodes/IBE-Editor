package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.ibeeditor.editor.AbstractProperty;
import com.github.franckyi.ibeeditor.editor.EditablePropertyList;
import com.github.franckyi.ibeeditor.editor.OrderableEditablePropertyListChild;
import com.github.franckyi.ibeeditor.editor.property.BooleanProperty;
import com.github.franckyi.ibeeditor.editor.property.FormattedTextProperty;
import com.github.franckyi.ibeeditor.editor.property.IntegerProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

import java.util.function.Consumer;

public class GeneralPropertyList extends EditablePropertyList<String> {

    private ItemStack itemStack;

    public GeneralPropertyList(ItemStack itemStack) {
        super(4);
        this.itemStack = itemStack;
        this.addAll(
                new BooleanProperty("Unbreakable", this.hasUnbreakable(), this::setUnbreakable),
                new IntegerProperty("Count", itemStack.getCount(), itemStack::setCount, 1, 128),
                new IntegerProperty("Damage", itemStack.getDamage(), itemStack::setDamage),
                new FormattedTextProperty("Name", itemStack.getDisplayName().getFormattedText(),
                        s -> itemStack.setDisplayName(new TextComponentString(s))),
                new AddButton("Add lore")
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

    private boolean hasUnbreakable() {
        return itemStack.getOrCreateTag().contains("Unbreakable", Constants.NBT.TAG_BYTE);
    }

    @Override
    public void apply() {
        itemStack.getOrCreateChildTag("display").put("Lore", new NBTTagList());
        itemStack.getOrCreateTag().remove("Unbreakable");
        super.apply();
    }

    private void setLore(String lore) {
        itemStack.getOrCreateChildTag("display")
                .getList("Lore", Constants.NBT.TAG_STRING).add(new NBTTagString(lore));
    }

    private void setUnbreakable(boolean unbreakable) {
        if (unbreakable) {
            itemStack.getOrCreateTag().putBoolean("Unbreakable", true);
        }
    }

    @Override
    protected AbstractProperty<String> createNewProperty(String initialValue, int index) {
        return new LoreProperty(index, initialValue, this::setLore);
    }

    @Override
    protected String getDefaultPropertyValue() {
        return "";
    }

    public class LoreProperty extends FormattedTextProperty implements OrderableEditablePropertyListChild {

        private final OrderableListControls controls;

        public LoreProperty(int index, String value, Consumer<String> action) {
            super("", value, action);
            this.controls = new OrderableListControls(GeneralPropertyList.this, index);
            OrderableEditablePropertyListChild.super.build();
        }

        @Override
        public OrderableListControls getControls() {
            return controls;
        }

        @Override
        public void build() {
            super.build();
        }

        @Override
        public void update(int newIndex) {
            OrderableEditablePropertyListChild.super.update(newIndex);
            nameLabel.setText("Lore #" + (newIndex + 1) + " :");
        }

        @Override
        protected void updateSize(int listWidth) {
            textField.setPrefWidth(listWidth - 196);
        }

    }
}
