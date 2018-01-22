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

public class GuiItemEditor extends GuiEditor {

    private ItemStack itemStack = IBEUtil.getItem();
    // General
    private IntegerProperty damage;
    private IntegerProperty count;
    private BooleanProperty unbreakable;
    // Display
    private StringProperty name;
    private StringProperty[] lores = new StringProperty[IBEConfiguration.loreLinesCount];
    // Enchantments
    private List<IntegerProperty> enchantments = new ArrayList<>(EnchantmentsUtil.getEnchantments().size());
    // NBT
    private NBTTagCompound tagCompound = itemStack.getTagCompound();
    private NBTTagCompound displayTag = itemStack.getOrCreateSubCompound("display");
    private NBTTagList loresList = displayTag.getTagList("Lore", Constants.NBT.TAG_STRING);
    private NBTTagList enchantmentsList = itemStack.getEnchantmentTagList();
    // Data
    private Map<Enchantment, Integer> enchantmentsMap = EnchantmentsUtil.readNBT(enchantmentsList);

    protected GuiItemEditor(GuiScreen parentScreen) {
        super(parentScreen);
        damage = new IntegerProperty("Damage", itemStack::getItemDamage, itemStack::setItemDamage);
        count = new IntegerProperty("Count", itemStack::getCount, itemStack::setCount);
        unbreakable = new BooleanProperty("Unbreakable", () -> displayTag.getBoolean("Unbreakable"), (b) -> displayTag.setBoolean("Unbreakable", b));
        name = new StringProperty("Name", IBEUtil.formattedStringSupplier(itemStack::getDisplayName), IBEUtil.formattedStringConsumer(itemStack::setStackDisplayName));
        for(int i[] = {0}; i[0] < lores.length; ++i[0]) {
            lores[i[0]] = new StringProperty(String.format("Lore %d", i[0] + 1),
                    IBEUtil.formattedStringSupplier(() -> loresList.getStringTagAt(i[0]).equals("END") ? "" : loresList.getStringTagAt(i[0])),
                    IBEUtil.formattedStringConsumer((s) -> { if(!s.equals("§r")) loresList.appendTag(new NBTTagString(s));}));
        }
        EnchantmentsUtil.getEnchantments().forEach(enchantment -> enchantments.add(new IntegerProperty(I18n.format(enchantment.getName()),
                () -> enchantmentsMap.getOrDefault(enchantment, 0), (i) -> { if(i > 0) enchantmentsList.appendTag(EnchantmentsUtil.writeNBT(enchantment, i));})));
        setPropertiesMap(new PropertyFactory()
                .newCategory("General")
                    .addAll(damage, count, unbreakable)
                .nextCategory("Display")
                    .addAll(name)
                    .addAll(lores)
                .nextCategory("Enchantments")
                    .addAll(enchantments)
                .endCategoryAndCreate());
    }

    @Override
    protected void apply() {
        loresList = new NBTTagList();
        enchantmentsList = new NBTTagList();
        super.apply();
        displayTag.setTag("Lore", loresList);
        tagCompound.setTag("ench", enchantmentsList);

    }

    protected GuiItemEditor() {
        this(null);
    }

}
