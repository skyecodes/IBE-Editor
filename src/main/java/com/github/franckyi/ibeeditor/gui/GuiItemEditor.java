package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.IBEConfiguration;
import com.github.franckyi.ibeeditor.gui.property.BooleanProperty;
import com.github.franckyi.ibeeditor.gui.property.IntegerProperty;
import com.github.franckyi.ibeeditor.gui.property.PropertyFactory;
import com.github.franckyi.ibeeditor.gui.property.StringProperty;
import com.github.franckyi.ibeeditor.util.EnchantmentsUtil;
import com.github.franckyi.ibeeditor.util.IBEUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.franckyi.ibeeditor.IBEEditor.LOGGER;

public class GuiItemEditor extends GuiEditor {

    private final ItemStack itemStack = IBEUtil.getItem();
    // NBT
    private NBTTagCompound tagCompound = itemStack.getTagCompound();
    private final NBTTagCompound displayTag = itemStack.getOrCreateSubCompound("display");
    private NBTTagList loresList = displayTag.getTagList("Lore", Constants.NBT.TAG_STRING);
    private NBTTagList enchantmentsList = itemStack.getEnchantmentTagList();
    // Data
    private final Map<Enchantment, Integer> enchantmentsMap = EnchantmentsUtil.readNBT(enchantmentsList != null ? enchantmentsList : new NBTTagList());
    private int hideFlags = tagCompound != null ? tagCompound.getInteger("HideFlags") : 0;

    protected GuiItemEditor(GuiScreen parentScreen) {
        super(parentScreen);
        if(tagCompound == null) itemStack.setTagCompound(tagCompound = new NBTTagCompound());
        // General
        IntegerProperty damage = new IntegerProperty("Damage", itemStack::getItemDamage, itemStack::setItemDamage);
        IntegerProperty count = new IntegerProperty("Count", itemStack::getCount, itemStack::setCount);
        BooleanProperty unbreakable = new BooleanProperty("Unbreakable", () -> displayTag.getBoolean("Unbreakable"), (b) -> displayTag.setBoolean("Unbreakable", b));
        // Display
        StringProperty name = new StringProperty("Name", IBEUtil.formattedStringSupplier(itemStack::getDisplayName), IBEUtil.formattedStringConsumer(itemStack::setStackDisplayName));
        StringProperty[] lores = new StringProperty[IBEConfiguration.loreLinesCount];
        for (int i[] = {0}; i[0] < lores.length; ++i[0]) {
            lores[i[0]] = new StringProperty(String.format("Lore %d", i[0] + 1),
                    IBEUtil.formattedStringSupplier(() -> loresList.getStringTagAt(i[0]).equals("END") ? "" : loresList.getStringTagAt(i[0])),
                    IBEUtil.formattedStringConsumer((s) -> {
                        if (!s.equals("§r")) loresList.appendTag(new NBTTagString(s));
                    }));
        }
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

        setPropertiesMap(new PropertyFactory()
                .newCategory("General")
                    .addAll(damage, count, unbreakable)
                .nextCategory("Display")
                    .addAll(name)
                    .addAll(lores)
                .nextCategory("Hide Flags")
                    .addAll(hideEnchantments, hideAttributeModifiers, hideUnbreakable, hideCanDestroy, hideCanPlaceOn, hideMisc)
                .nextCategory("Enchantments")
                    .addAll(enchantments)
                .endCategoryAndCreate());
    }

    private void addHideFlags(int i) {
        hideFlags += i;
    }

    private boolean hasHideFlags(int i) {
        return String.format("%6s", Integer.toBinaryString(hideFlags)).replace(" ", "0").charAt(i) == '1';
    }

    @Override
    protected void apply() {
        LOGGER.info("Preparing to apply...");
        loresList = new NBTTagList();
        enchantmentsList = new NBTTagList();
        hideFlags = 0;
        super.apply();
        displayTag.setTag("Lore", loresList);
        tagCompound.setInteger("HideFlags", hideFlags);
        tagCompound.setTag("ench", enchantmentsList);
        LOGGER.info("Done !");
    }

    protected GuiItemEditor() {
        this(null);
    }

}
