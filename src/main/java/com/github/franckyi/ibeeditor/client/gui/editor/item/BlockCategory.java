package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.IEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.LabeledProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class BlockCategory extends EditableCategory<Block> {

    private final ItemStack itemStack;
    private final String tagName;
    private boolean flag;

    public BlockCategory(ItemStack itemStack, String tagName) {
        this.itemStack = itemStack;
        this.tagName = tagName;
        this.getChildren().add(new AddButton("Add block"));
        CompoundNBT tag = itemStack.getOrCreateTag();
        if (tag.contains(tagName, Constants.NBT.TAG_LIST)) {
            ListNBT list = tag.getList(tagName, Constants.NBT.TAG_STRING);
            for (int i = 0; i < list.size(); i++) {
                this.addProperty(stringToBlock(list.getString(i)));
            }
        }
    }

    public static Block stringToBlock(String str) {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryCreate(str));
    }

    @Override
    public void apply() {
        itemStack.getOrCreateTag().remove(tagName);
        flag = true;
        super.apply();
    }

    private void addBlock(Block block) {
        if (flag) {
            itemStack.getOrCreateTag().put(tagName, new ListNBT());
            flag = false;
        }
        itemStack.getOrCreateTag().getList(tagName, Constants.NBT.TAG_STRING)
                .add(new StringNBT(block.getRegistryName().toString()));
    }

    @Override
    protected AbstractProperty<Block> createNewProperty(Block initialValue, int index) {
        return new PropertyBlock(index, initialValue, this::addBlock);
    }

    @Override
    protected Block getDefaultPropertyValue() {
        return Blocks.AIR;
    }

    public class PropertyBlock extends LabeledProperty<Block> implements IEditableCategoryProperty {

        private PropertyControls controls;

        private TextField blockField;

        public PropertyBlock(int index, Block initialValue, Consumer<Block> action) {
            super(initialValue.getNameTextComponent().getUnformattedComponentText(), initialValue, action, Node.COMPUTED_SIZE);
            controls = new PropertyControls(BlockCategory.this, index);
            IEditableCategoryProperty.super.build();
        }

        @Override
        protected Block getValue() {
            return stringToBlock(blockField.getValue());
        }

        @Override
        protected void setValue(Block value) {
            blockField.setValue(value.getRegistryName().toString());
        }

        @Override
        public PropertyControls getControls() {
            return controls;
        }

        @Override
        public void build() {
            super.build();
            this.getNode().setAlignment(Pos.RIGHT);
            this.addAll(blockField = new TextField());
            blockField.getOnCharTypedListeners().add(e -> update());
            blockField.getOnKeyPressedListeners().add(e -> update());
        }

        @Override
        public void updateSize(int listWidth) {
            blockField.setPrefWidth(listWidth / 2 - 35);
        }

        private void update() {
            nameLabel.setText(getValue().getNameTextComponent().getUnformattedComponentText() + " :");
            this.updateChildrenPos();
        }

    }

}
