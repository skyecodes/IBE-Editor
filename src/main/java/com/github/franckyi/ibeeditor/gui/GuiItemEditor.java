package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.IBEEditor;
import com.github.franckyi.ibeeditor.gui.child.GuiPropertyListAttributeModifier;
import com.github.franckyi.ibeeditor.gui.child.GuiPropertyListItemDisplay;
import com.github.franckyi.ibeeditor.gui.property.*;
import com.github.franckyi.ibeeditor.network.UpdateItemMessage;
import com.github.franckyi.ibeeditor.util.AttributeModifierModel;
import com.github.franckyi.ibeeditor.util.EnchantmentsUtil;
import com.github.franckyi.ibeeditor.util.IBEUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.github.franckyi.ibeeditor.IBEEditor.logger;

public class GuiItemEditor extends GuiEditor {

    // Item
    private ItemStack itemStack;
    private int slotId;
    private BlockPos blockPos;
    // NBT
    private NBTTagCompound tagCompound;
    private NBTTagCompound displayTag;
    private NBTTagList loresList;
    private NBTTagList enchantmentsList;
    // Data
    private final Map<Enchantment, Integer> enchantmentsMap;
    private int hideFlags;

    public GuiItemEditor(GuiScreen parentScreen, ItemStack itemStack, int slotId, BlockPos blockPos) {
        // Init item
        super(parentScreen);
        this.itemStack = itemStack;
        this.slotId = slotId;
        this.blockPos = blockPos;
        // Init NBT
        if(itemStack.getTagCompound() == null) itemStack.setTagCompound(new NBTTagCompound());
        tagCompound = itemStack.getTagCompound();
        displayTag = itemStack.getOrCreateSubCompound("display");
        loresList = displayTag.getTagList("Lore", Constants.NBT.TAG_STRING);
        enchantmentsList = itemStack.getEnchantmentTagList();
        // Init data
        enchantmentsMap = EnchantmentsUtil.readNBT(enchantmentsList != null ? enchantmentsList : new NBTTagList());
        hideFlags = tagCompound != null ? tagCompound.getInteger("HideFlags") : 0;
        // General
        IntegerProperty damage = new IntegerProperty("Damage", this.itemStack::getItemDamage, this.itemStack::setItemDamage);
        IntegerProperty count = new IntegerProperty("Count", this.itemStack::getCount, this.itemStack::setCount);
        BooleanProperty unbreakable = new BooleanProperty("Unbreakable", () -> tagCompound.getByte("Unbreakable") == 1, (b) -> tagCompound.setByte("Unbreakable", (byte) (b ? 1 : 0)));
        // Display
        StringProperty name = new StringProperty("Name", () -> IBEUtil.unformatString(this.itemStack.getDisplayName()), s -> {});
        List<StringProperty> lores = new ArrayList<>(loresList.tagCount());
        loresList.forEach(nbtBase -> lores.add(new StringProperty("", () -> IBEUtil.unformatString(((NBTTagString)nbtBase).getString()), s -> {})));
        // Hide Flags
        BooleanProperty hideEnchantments = new BooleanProperty("Hide Enchantments", () -> hasHideFlags(5), (b) -> addHideFlags(b ? 1 : 0));
        BooleanProperty hideAttributeModifiers = new BooleanProperty("Hide Attribute Modifiers", () -> hasHideFlags(4), (b) -> addHideFlags(b ? 2 : 0));
        BooleanProperty hideUnbreakable = new BooleanProperty("Hide Unbreakable", () -> hasHideFlags(3), (b) -> addHideFlags(b ? 4 : 0));
        BooleanProperty hideCanDestroy = new BooleanProperty("Hide 'Can destroy'", () -> hasHideFlags(2), (b) -> addHideFlags(b ? 8 : 0));
        BooleanProperty hideCanPlaceOn = new BooleanProperty("Hide 'Can place on'", () -> hasHideFlags(1), (b) -> addHideFlags(b ? 16 : 0));
        BooleanProperty hideMisc = new BooleanProperty("Hide Miscellaneous", () -> hasHideFlags(0), (b) -> addHideFlags(b ? 32 : 0));
        // Enchantments
        List<IntegerProperty> enchantments = new ArrayList<>(EnchantmentsUtil.getEnchantments().size());
        EnchantmentsUtil.getEnchantments().forEach(enchantment -> enchantments.add(new IntegerProperty(I18n.format(enchantment.getName()),
                () -> enchantmentsMap.getOrDefault(enchantment, 0), (i) -> {
            if (i > 0) enchantmentsList.appendTag(EnchantmentsUtil.writeNBT(enchantment, i));
        })));
        // Attribute modifiers
        List<AttributeModifierProperty> attributeModifiers = new ArrayList<>();
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            itemStack.getAttributeModifiers(slot).values().forEach(attributeModifier -> attributeModifiers.add(new AttributeModifierProperty(() -> new AttributeModifierModel(attributeModifier, slot))));
        }
        setCategories(Arrays.asList(new PropertyCategory("General")
                    .addAll(damage, count, unbreakable),
                new PropertyCategory("Display", GuiPropertyListItemDisplay::new, this::applyDisplay)
                    .addAll(name)
                    .addAll(lores),
                new PropertyCategory("Hide Flags")
                    .addAll(hideEnchantments, hideAttributeModifiers, hideUnbreakable, hideCanDestroy, hideCanPlaceOn, hideMisc),
                new PropertyCategory("Enchantments")
                        .addAll(enchantments),
                new PropertyCategory("Attribute Modifiers", GuiPropertyListAttributeModifier::new, this::applyAttributeModifiers)
        ));
    }

    private void addHideFlags(int i) {
        hideFlags += i;
    }

    private boolean hasHideFlags(int i) {
        return String.format("%6s", Integer.toBinaryString(hideFlags)).replace(" ", "0").charAt(i) == '1';
    }

    @Override
    protected void apply() {
        logger.info("Preparing to apply...");
        loresList = new NBTTagList();
        hideFlags = 0;
        enchantmentsList = new NBTTagList();
        super.apply();
        displayTag.setTag("Lore", loresList);
        tagCompound.setInteger("HideFlags", hideFlags);
        tagCompound.setTag("ench", enchantmentsList);
        IBEEditor.netwrapper.sendToServer(new UpdateItemMessage(itemStack, slotId, blockPos));
        logger.info("Done !");
    }

    private void applyDisplay(List<BaseProperty<?>> properties) {
        for(int i = 0; i < properties.size(); ++i) {
            StringProperty property = (StringProperty) properties.get(i);
            if(i == 0) {
                itemStack.setStackDisplayName(IBEUtil.formatString(property.getValue()));
            } else {
                loresList.appendTag(new NBTTagString(IBEUtil.formatString(property.getValue())));
            }
        }
    }

    private void applyAttributeModifiers(List<BaseProperty<?>> properties) {
        properties.forEach(property -> {
            AttributeModifierModel model = ((AttributeModifierProperty) property).getValue();
            if (model.toAttributeModifier() == null || model.getName().isEmpty()) {
                logger.warn("Unable to create attribute modifier for attribute {} {} {} {}", model.getName(), model.getAmount(), model.getOperation(), model.getSlot());
            } else {
                itemStack.addAttributeModifier(model.getName(), model.toAttributeModifier(), model.getSlot());
            }
        });
    }

    public GuiItemEditor(ItemStack itemStack, int slotId, BlockPos blockPos) {
        this(null, itemStack, slotId, blockPos);
    }

    public GuiItemEditor(ItemStack itemStack) {
        this(null, itemStack, Minecraft.getMinecraft().player.inventory.getSlotFor(itemStack), null);
    }

}
