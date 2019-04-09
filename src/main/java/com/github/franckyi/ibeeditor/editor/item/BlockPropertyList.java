package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;
import com.github.franckyi.ibeeditor.editor.EditablePropertyList;
import com.github.franckyi.ibeeditor.editor.EditablePropertyListChild;
import com.github.franckyi.ibeeditor.editor.property.EmptyProperty;
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

public class BlockPropertyList extends EditablePropertyList<Block> {

    private final ItemStack itemStack;
    private final String tagName;

    public BlockPropertyList(ItemStack itemStack, String tagName) {
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
        itemStack.getOrCreateTag().put(tagName, new NBTTagList());
        super.apply();
    }

    private void addBlock(Block block) {
        itemStack.getOrCreateTag().getList(tagName, Constants.NBT.TAG_STRING)
                .add(new NBTTagString(block.getRegistryName().toString()));
    }

    @Override
    protected AbstractProperty<Block> createNewProperty(Block initialValue, int index) {
        return new BlockProperty(index, initialValue, this::addBlock);
    }

    @Override
    protected Block getDefaultPropertyValue() {
        return Blocks.AIR;
    }

    public class BlockProperty extends EmptyProperty<Block> implements EditablePropertyListChild {

        private ListControls controls;

        private TextField blockField;

        public BlockProperty(int index, Block initialValue, Consumer<Block> action) {
            super(initialValue.getNameTextComponent().getUnformattedComponentText(), initialValue, action);
            controls = new ListControls(BlockPropertyList.this, index);
            EditablePropertyListChild.super.build();
        }

        @Override
        protected Block getValue() {
            return stringToBlock(blockField.getText());
        }

        @Override
        protected void setValue(Block value) {
            blockField.setText(value.getRegistryName().toString());
        }

        @Override
        public ListControls getControls() {
            return controls;
        }

        @Override
        public void build() {
            super.build();
            this.getNode().setAlignment(Pos.RIGHT);
            this.getNode().getChildren().add(blockField = new TextField());
            blockField.getOnCharTypedListeners().add(e -> update());
            blockField.getOnKeyPressedListeners().add(e -> update());
            nameLabel.setPrefWidth(Node.COMPUTED_SIZE);
        }

        @Override
        protected void updateSize(int listWidth) {
            blockField.setPrefWidth(listWidth / 2 - 35);
        }

        private void update() {
            nameLabel.setText(getValue().getNameTextComponent().getUnformattedComponentText() + " :");
        }

    }

}
