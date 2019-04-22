package com.github.franckyi.ibeeditor.client.editor.item;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.client.editor.AbstractProperty;
import com.github.franckyi.ibeeditor.client.editor.EditableCategory;
import com.github.franckyi.ibeeditor.client.editor.IEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.editor.property.LabeledCategory;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class BlockCategory extends EditableCategory<Block> {

    private final ItemStack itemStack;
    private final String tagName;

    public BlockCategory(ItemStack itemStack, String tagName) {
        this.itemStack = itemStack;
        this.tagName = tagName;
        this.getChildren().add(new AddButton("Add block"));
        NBTTagCompound tag = itemStack.getOrCreateTag();
        if (tag.contains(tagName, Constants.NBT.TAG_LIST)) {
            NBTTagList list = tag.getList(tagName, Constants.NBT.TAG_STRING);
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
        super.apply();
    }

    private void addBlock(Block block) {
        itemStack.getOrCreateTag().getList(tagName, Constants.NBT.TAG_STRING)
                .add(new NBTTagString(block.getRegistryName().toString()));
    }

    @Override
    protected AbstractProperty<Block> createNewProperty(Block initialValue, int index) {
        return new PropertyBlock(index, initialValue, this::addBlock);
    }

    @Override
    protected Block getDefaultPropertyValue() {
        return Blocks.AIR;
    }

    public class PropertyBlock extends LabeledCategory<Block> implements IEditableCategoryProperty {

        private PropertyControls controls;

        private TextField blockField;

        public PropertyBlock(int index, Block initialValue, Consumer<Block> action) {
            super(initialValue.getNameTextComponent().getUnformattedComponentText(), initialValue, action);
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
            nameLabel.setPrefWidth(Node.COMPUTED_SIZE);
        }

        @Override
        public void updateSize(int listWidth) {
            blockField.setPrefWidth(listWidth / 2 - 35);
        }

        private void update() {
            nameLabel.setText(getValue().getNameTextComponent().getUnformattedComponentText() + " :");
        }

    }

}
