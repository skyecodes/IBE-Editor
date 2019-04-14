package com.github.franckyi.ibeeditor.client.item;

import com.github.franckyi.ibeeditor.client.AbstractProperty;
import com.github.franckyi.ibeeditor.client.EditableCategory;
import com.github.franckyi.ibeeditor.client.OrderableEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.property.PropertyInteger;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

import java.util.function.Consumer;

public class GeneralItemCategory extends EditableCategory<String> {

    private final ItemStack itemStack;

    public GeneralItemCategory(ItemStack itemStack) {
        super(4);
        this.itemStack = itemStack;
        this.addAll(
                new PropertyBoolean("Unbreakable", this.hasUnbreakable(), this::setUnbreakable),
                new PropertyInteger("Count", itemStack.getCount(), itemStack::setCount, 1, 128),
                new PropertyInteger("Damage", itemStack.getDamage(), itemStack::setDamage),
                new PropertyFormattedText("Name", itemStack.getDisplayName().getFormattedText(),
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
        return new PropertyLore(index, initialValue, this::setLore);
    }

    @Override
    protected String getDefaultPropertyValue() {
        return "";
    }

    public class PropertyLore extends PropertyFormattedText implements OrderableEditableCategoryProperty {

        private final OrderablePropertyControls controls;

        public PropertyLore(int index, String value, Consumer<String> action) {
            super("", value, action);
            this.controls = new OrderablePropertyControls(GeneralItemCategory.this, index);
            OrderableEditableCategoryProperty.super.build();
        }

        @Override
        public OrderablePropertyControls getControls() {
            return controls;
        }

        @Override
        public void build() {
            super.build();
        }

        @Override
        public void update(int newIndex) {
            OrderableEditableCategoryProperty.super.update(newIndex);
            nameLabel.setText("Lore #" + (newIndex + 1) + " :");
        }

        @Override
        protected void updateSize(int listWidth) {
            textField.setPrefWidth(listWidth - 196);
        }

    }
}
