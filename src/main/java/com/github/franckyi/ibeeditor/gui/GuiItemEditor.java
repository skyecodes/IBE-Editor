package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.IBEEditor;
import com.github.franckyi.ibeeditor.gui.child.item.GuiPropertyListAttributeModifier;
import com.github.franckyi.ibeeditor.gui.child.item.GuiPropertyListBlock;
import com.github.franckyi.ibeeditor.gui.child.item.GuiPropertyListDisplay;
import com.github.franckyi.ibeeditor.gui.child.item.GuiPropertyListPotionEffects;
import com.github.franckyi.ibeeditor.gui.property.PropertyCategory;
import com.github.franckyi.ibeeditor.gui.property.base.BooleanProperty;
import com.github.franckyi.ibeeditor.gui.property.base.FormattedStringProperty;
import com.github.franckyi.ibeeditor.gui.property.base.IntegerProperty;
import com.github.franckyi.ibeeditor.gui.property.base.StringProperty;
import com.github.franckyi.ibeeditor.gui.property.item.AttributeModifierProperty;
import com.github.franckyi.ibeeditor.gui.property.item.PotionEffectProperty;
import com.github.franckyi.ibeeditor.models.AttributeModifierModel;
import com.github.franckyi.ibeeditor.models.PotionEffectModel;
import com.github.franckyi.ibeeditor.network.UpdateItemMessage;
import com.github.franckyi.ibeeditor.util.EnchantmentsUtil;
import com.github.franckyi.ibeeditor.util.IBEUtil;
import com.github.franckyi.ibeeditor.util.item.BaseItemHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.franckyi.ibeeditor.IBEEditor.logger;

public class GuiItemEditor extends GuiEditor {

    private final BaseItemHandler itemHandler;

    // Item
    private final ItemStack itemStack;
    // NBT
    private final NBTTagCompound tagCompound;
    private final NBTTagCompound displayTag;
    private NBTTagList loresList;
    private NBTTagList enchantmentsList;
    private NBTTagList canDestroy;
    private NBTTagList canPlaceOn;
    private NBTTagList potionEffects;
    // Data
    private Map<Enchantment, Integer> enchantmentsMap;
    private int hideFlags;

    public GuiItemEditor(BaseItemHandler itemHandler) {
        this(null, itemHandler);
    }

    public GuiItemEditor(GuiScreen parentScreen, BaseItemHandler itemHandler) {
        // Init item
        super(parentScreen);
        this.itemHandler = itemHandler;
        this.itemStack = itemHandler.getItemStack();
        // Init NBT
        if (itemStack.getTagCompound() == null) itemStack.setTagCompound(new NBTTagCompound());
        tagCompound = itemStack.getTagCompound();
        displayTag = itemStack.getOrCreateSubCompound("display");
        loresList = displayTag.getTagList("Lore", Constants.NBT.TAG_STRING);
        enchantmentsList = itemStack.getEnchantmentTagList();
        canDestroy = tagCompound.getTagList("CanDestroy", Constants.NBT.TAG_STRING);
        // Init data
        enchantmentsMap = EnchantmentsUtil.readNBT(enchantmentsList);
        hideFlags = tagCompound.getInteger("HideFlags");
        boolean isBlock = itemStack.getItem() instanceof ItemBlock;
        boolean isPotion = itemStack.getItem() instanceof ItemPotion || itemStack.getItem() instanceof ItemSplashPotion ||
                itemStack.getItem() instanceof ItemLingeringPotion || itemStack.getItem() instanceof ItemTippedArrow;
        // General
        IntegerProperty damage = new IntegerProperty("Damage", this.itemStack::getItemDamage, this.itemStack::setItemDamage);
        IntegerProperty count = new IntegerProperty("Count", this.itemStack::getCount, this.itemStack::setCount);
        BooleanProperty unbreakable = new BooleanProperty("Unbreakable", () -> tagCompound.getByte("Unbreakable") == 1, (b) -> tagCompound.setByte("Unbreakable", (byte) (b ? 1 : 0)));
        categories.add(new PropertyCategory<>("General").addAll(damage, count, unbreakable));
        // Display
        FormattedStringProperty name = new FormattedStringProperty("Name", () -> IBEUtil.unformatString(this.itemStack.getDisplayName()), s -> {
        });
        List<FormattedStringProperty> lores = new ArrayList<>(loresList.tagCount());
        loresList.forEach(nbtBase -> lores.add(new FormattedStringProperty("", () -> IBEUtil.unformatString(((NBTTagString) nbtBase).getString()), s -> {
        })));
        categories.add(new PropertyCategory<>("Display", GuiPropertyListDisplay::new, this::applyDisplay).addAll(name).addAll(lores));
        // Hide Flags
        BooleanProperty hideEnchantments = new BooleanProperty("Hide Enchantments", () -> hasHideFlags(5), (b) -> addHideFlags(b ? 1 : 0));
        BooleanProperty hideAttributeModifiers = new BooleanProperty("Hide Attribute Modifiers", () -> hasHideFlags(4), (b) -> addHideFlags(b ? 2 : 0));
        BooleanProperty hideUnbreakable = new BooleanProperty("Hide Unbreakable", () -> hasHideFlags(3), (b) -> addHideFlags(b ? 4 : 0));
        BooleanProperty hideCanDestroy = new BooleanProperty("Hide 'Can destroy'", () -> hasHideFlags(2), (b) -> addHideFlags(b ? 8 : 0));
        BooleanProperty hideCanPlaceOn = new BooleanProperty("Hide 'Can place on'", () -> hasHideFlags(1), (b) -> addHideFlags(b ? 16 : 0));
        BooleanProperty hideMisc = new BooleanProperty("Hide Miscellaneous", () -> hasHideFlags(0), (b) -> addHideFlags(b ? 32 : 0));
        categories.add(new PropertyCategory<BooleanProperty>("Hide Flags").addAll(hideEnchantments, hideAttributeModifiers, hideUnbreakable, hideCanDestroy, hideCanPlaceOn, hideMisc));
        // Enchantments
        List<IntegerProperty> enchantments = new ArrayList<>(EnchantmentsUtil.getEnchantments().size());
        EnchantmentsUtil.getEnchantments().sort((ench, ench1) -> {
            Item item = itemStack.getItem();
            if (ench.type == null || ench1.type == null) return 0;
            if (ench.type.canEnchantItem(item)
                    && !ench1.type.canEnchantItem(item))
                return -1;
            if (!ench.type.canEnchantItem(item)
                    && ench1.type.canEnchantItem(item))
                return 1;
            return 0;
        });
        EnchantmentsUtil.getEnchantments().forEach(enchantment -> {
            String color = (enchantment.type != null && enchantment.type.canEnchantItem(itemStack.getItem())) ? "§2" : "";
            enchantments.add(new IntegerProperty(color + I18n.format(enchantment.getName()),
                    () -> enchantmentsMap.getOrDefault(enchantment, 0), (i) -> {
                if (i > 0) enchantmentsList.appendTag(EnchantmentsUtil.writeNBT(enchantment, i));
            }, 0, Short.MAX_VALUE));
        });
        categories.add(new PropertyCategory<IntegerProperty>("Enchantments").addAll(enchantments));
        // Attribute modifiers
        List<AttributeModifierProperty> attributeModifiers = new ArrayList<>();
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            itemStack.getAttributeModifiers(slot).entries().forEach(entry -> attributeModifiers.add(new AttributeModifierProperty(() ->
                    new AttributeModifierModel(entry.getKey(), entry.getValue().getAmount(), entry.getValue().getOperation(), slot))));
        }
        categories.add(new PropertyCategory<>("Attribute Modifiers", GuiPropertyListAttributeModifier::new, this::applyAttributeModifiers).addAll(attributeModifiers));
        // Can Destroy
        List<StringProperty> canDestroyList = new ArrayList<>(canDestroy.tagCount());
        canDestroy.forEach(nbtBase -> canDestroyList.add(new StringProperty("", ((NBTTagString) nbtBase)::getString, s -> {
        })));
        categories.add(new PropertyCategory<>("Can Destroy", GuiPropertyListBlock::new, this::applyCanDestroy).addAll(canDestroyList));
        if (isBlock) {
            // Can Place On
            canPlaceOn = tagCompound.getTagList("CanPlaceOn", Constants.NBT.TAG_STRING);
            List<StringProperty> canPlaceOnList = new ArrayList<>(canDestroy.tagCount());
            canPlaceOn.forEach(nbtBase -> canPlaceOnList.add(new StringProperty("", ((NBTTagString) nbtBase)::getString, s -> {
            })));
            categories.add(new PropertyCategory<>("Can Place On", GuiPropertyListBlock::new, this::applyCanPlaceOn).addAll(canPlaceOnList));
        }
        if (isPotion) {
            // Potion Effects
            potionEffects = tagCompound.getTagList("CustomPotionEffects", Constants.NBT.TAG_COMPOUND);
            List<PotionEffectProperty> potionEffectList = new ArrayList<>(potionEffects.tagCount());
            potionEffects.forEach(nbtBase -> potionEffectList.add(new PotionEffectProperty(() -> PotionEffectModel.fromNBT(((NBTTagCompound) nbtBase)))));
            categories.add(new PropertyCategory<>("Potion Effects", GuiPropertyListPotionEffects::new, this::applyPotionEffects).addAll(potionEffectList));
        }
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
        hideFlags = 0;
        enchantmentsList = new NBTTagList();
        super.apply();
        tagCompound.setInteger("HideFlags", hideFlags);
        tagCompound.setTag("ench", enchantmentsList);
        itemHandler.setItemStack(itemStack);
        IBEEditor.netwrapper.sendToServer(new UpdateItemMessage(itemHandler));
        logger.info("Done !");
    }

    private void applyDisplay(List<FormattedStringProperty> properties) {
        loresList = new NBTTagList();
        for (int i = 0; i < properties.size(); ++i) {
            StringProperty property = properties.get(i);
            if (i == 0) {
                itemStack.setStackDisplayName(IBEUtil.formatString(property.getValue()));
            } else {
                loresList.appendTag(new NBTTagString(IBEUtil.formatString(property.getValue())));
            }
        }
        displayTag.setTag("Lore", loresList);
    }

    private void applyAttributeModifiers(List<AttributeModifierProperty> properties) {
        tagCompound.removeTag("AttributeModifiers");
        properties.forEach(property -> {
            AttributeModifierModel model = property.getValue();
            if (model.toAttributeModifier() == null || model.getName().isEmpty()) {
                logger.warn("Unable to create attribute modifier for attribute {} {} {} {}", model.getName(), model.getAmount(), model.getOperation(), model.getSlot());
            } else {
                itemStack.addAttributeModifier(model.getName(), model.toAttributeModifier(), model.getSlot());
            }
        });
    }

    private void applyCanDestroy(List<StringProperty> properties) {
        canDestroy = new NBTTagList();
        properties.forEach(property -> canDestroy.appendTag(new NBTTagString(property.getValue())));
        tagCompound.setTag("CanDestroy", canDestroy);
    }

    private void applyCanPlaceOn(List<StringProperty> properties) {
        canPlaceOn = new NBTTagList();
        properties.forEach(property -> canPlaceOn.appendTag(new NBTTagString(property.getValue())));
        tagCompound.setTag("CanPlaceOn", canPlaceOn);
    }

    private void applyPotionEffects(List<PotionEffectProperty> properties) {
        potionEffects = new NBTTagList();
        properties.forEach(property -> potionEffects.appendTag(property.getValue().toNBT()));
        tagCompound.setTag("CustomPotionEffects", potionEffects);
    }

}
