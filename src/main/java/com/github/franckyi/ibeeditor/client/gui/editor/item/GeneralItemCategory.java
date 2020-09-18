package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.IOrderableEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyInteger;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

import java.util.function.Consumer;

public class GeneralItemCategory extends EditableCategory<String> {

    private final ItemStack itemStack;
    private boolean flag;

    public GeneralItemCategory(ItemStack itemStack) {
        super(4);
        this.itemStack = itemStack;
        this.addAll(
                new PropertyFormattedText("Name", itemStack.getDisplayName().getString(), this::setName),
                new PropertyBoolean("Unbreakable", this.hasUnbreakable(), this::setUnbreakable),
                new PropertyInteger("Count", itemStack.getCount(), itemStack::setCount, 1, 127),
                new PropertyInteger("Damage", itemStack.getDamage(), this::setDamage),
                new AddButton("Add lore")
        );
        if (itemStack.hasTag()) {
            CompoundNBT tag = itemStack.getOrCreateTag();
            if (tag.contains("display", Constants.NBT.TAG_COMPOUND)) {
                CompoundNBT display = itemStack.getOrCreateChildTag("display");
                if (display.contains("Lore", Constants.NBT.TAG_LIST)) {
                    ListNBT loreTag = display.getList("Lore", Constants.NBT.TAG_STRING);
                    for (int i = 0; i < loreTag.size(); i++) {
                        JsonObject obj = JSONUtils.fromJson(loreTag.getString(i));
                        this.addProperty(obj.getAsJsonPrimitive("text").getAsString());
                    }
                    for (int i = 0; i < this.getPropertyCount(); i++) {
                        this.getProperty(i).update(i);
                    }
                }
            }
        }
    }

    private void setDamage(int damage) {
        if (damage == 0) {
            itemStack.getOrCreateTag().remove("Damage");
        } else {
            itemStack.setDamage(damage);
        }
    }

    private void setName(String s) {
        TranslationTextComponent baseName = new TranslationTextComponent(itemStack.getItem().getTranslationKey(itemStack));
        if (baseName.getString().equals(s)) {
            CompoundNBT display = itemStack.getChildTag("display");
            if (display != null) {
                display.remove("Name");
            }
        } else {
            itemStack.setDisplayName(ITextComponent.getTextComponentOrEmpty(s));
        }
    }

    private boolean hasUnbreakable() {
        return itemStack.getOrCreateTag().contains("Unbreakable", Constants.NBT.TAG_BYTE);
    }

    @Override
    public void apply() {
        itemStack.getOrCreateTag().remove("Unbreakable");
        flag = true;
        super.apply();
    }

    private void setLore(String lore) {
        if (flag) {
            itemStack.getOrCreateChildTag("display").put("Lore", new ListNBT());
            flag = false;
        }
        itemStack.getOrCreateChildTag("display")
                .getList("Lore", Constants.NBT.TAG_STRING).add(StringNBT.valueOf("{\"text\": \"" + lore + "\"}"));
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

    private static class Lore {
        private final String text;

        public Lore(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public class PropertyLore extends PropertyFormattedText implements IOrderableEditableCategoryProperty {

        private final OrderablePropertyControls controls;

        public PropertyLore(int index, String value, Consumer<String> action) {
            super("", value, action);
            this.controls = new OrderablePropertyControls(GeneralItemCategory.this, index);
            IOrderableEditableCategoryProperty.super.build();
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
            IOrderableEditableCategoryProperty.super.update(newIndex);
            nameLabel.setText("Lore #" + (newIndex + 1));
        }

        @Override
        public void updateSize(int listWidth) {
            textField.setPrefWidth(listWidth - OFFSET - 198);
        }

    }
}
