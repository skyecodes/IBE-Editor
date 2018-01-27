package com.github.franckyi.ibeeditor.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnchantmentsUtil {

    private static final List<Enchantment> enchantments = new ArrayList<>();

    public static List<Enchantment> getEnchantments() {
        return enchantments;
    }

    public static Map<Enchantment, Integer> readNBT(NBTTagList list) {
        Map<Enchantment, Integer> enchants = new LinkedHashMap<>();
        list.forEach(nbtBase -> {
            NBTTagCompound c = ((NBTTagCompound) nbtBase);
            enchants.put(Enchantment.getEnchantmentByID(c.getShort("id")), (int) c.getShort("lvl"));
        });
        return enchants;
    }

    public static NBTTagCompound writeNBT(Enchantment enchantment, int lvl) {
        NBTTagCompound c = new NBTTagCompound();
        c.setShort("id", (short) Enchantment.getEnchantmentID(enchantment));
        c.setShort("lvl", (short) lvl);
        return c;
    }
}
