package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.IBENotification;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.CapabilityProviderEditor;
import com.github.franckyi.ibeeditor.client.gui.editor.block.BlockStateCategory;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ItemEditor extends CapabilityProviderEditor implements BlockStateCategory.IBlockStateContainer {
    private final ItemStack itemStack;
    private BlockState blockState;
    private final Predicate<ItemStack> action;

    public static void withConsumer(ItemStack itemStack, Consumer<ItemStack> action) {
        new ItemEditor(itemStack, itemStack1 -> {
            action.accept(itemStack1);
            return true;
        });
    }

    public static void withPredicate(ItemStack itemStack, Predicate<ItemStack> action) {
        new ItemEditor(itemStack, action);
    }

    private ItemEditor(ItemStack itemStack, Predicate<ItemStack> action) {
        super("Item Editor :");
        this.itemStack = itemStack;
        this.action = action;
        header.getChildren().add(new TexturedButton(itemStack));
        this.addCategory("General", new GeneralItemCategory(itemStack));
        if (itemStack.getItem() instanceof PotionItem || itemStack.getItem() instanceof TippedArrowItem) {
            this.addCategory("Potion effects", new PotionCategory(itemStack));
        }
        if (itemStack.getItem() instanceof SpawnEggItem) {
            this.addCategory("Spawn entity", new SpawnEggCategory(itemStack, (SpawnEggItem) itemStack.getItem()));
        }
        if (itemStack.getItem() instanceof BlockItem) {
            this.initBlock(((BlockItem) itemStack.getItem()));
            this.addCategory("Block State", new BlockStateCategory(this, this::applyBlock));
        }
        this.applyConfigurations(this.getCapabilityConfigurations(), itemStack);
        this.addCategory("Enchantments", new EnchantmentsCategory(itemStack));
        this.addCategory("Attribute modifiers", new AttributeModifiersCategory(itemStack));
        this.addCategory("Hide Flags", new HideFlagsCategory(itemStack));
        if (itemStack.getItem() instanceof BlockItem) {
            this.addCategory("Can place on", new BlockCategory(itemStack, "CanPlaceOn"));
        }
        this.addCategory("Can destroy", new BlockCategory(itemStack, "CanDestroy"));
        this.addCategory("Tools", new ToolsItemCategory(itemStack));
        this.show();
    }

    @Override
    protected void apply() {
        ItemStack baseStack = itemStack.copy();
        propertiesList.subList(1, propertiesList.size()).forEach(AbstractCategory::apply);
        propertiesList.get(0).apply();
        header.getChildren().set(1, new TexturedButton(itemStack));
        if (baseStack.equals(itemStack, false)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.YELLOW + "Nothing to save.");
        } else {
            if (this.action.test(itemStack)) {
                IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Item saved.");
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initBlock(BlockItem item) {
        mc.mouseHelper.ungrabMouse();
        blockState = item.getBlock().getDefaultState();
        CompoundNBT tag = itemStack.getChildTag("BlockStateTag");
        if (tag != null) {
            blockState.getProperties().forEach(property -> {
                String name = property.getName();
                if (tag.contains(name)) {
                    if (property instanceof IntegerProperty) {
                        blockState = blockState.with((IntegerProperty) property, tag.getInt(name));
                    } else if (property instanceof BooleanProperty) {
                        blockState = blockState.with((BooleanProperty) property, tag.getString(name).equalsIgnoreCase("true"));
                    } else if (property instanceof EnumProperty<?>) {
                        EnumProperty property1 = (EnumProperty) property;
                        blockState = blockState.with(property1, (Comparable) property1.parseValue(tag.getString(name)).orElse(null));
                    }
                }
            });
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void applyBlock() {
        CompoundNBT tag = new CompoundNBT();
        blockState.getProperties().forEach(property -> {
            if (property instanceof IntegerProperty) {
                tag.putInt(property.getName(), blockState.get((IntegerProperty) property));
            } else if (property instanceof BooleanProperty) {
                tag.putString(property.getName(), blockState.get((BooleanProperty) property) ? "true" : "false");
            } else if (property instanceof EnumProperty<?>) {
                tag.putString(property.getName(), blockState.get((EnumProperty) property).toString());
            }
        });
        itemStack.getOrCreateTag().put("BlockStateTag", tag);
    }

    @Override
    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }
}
