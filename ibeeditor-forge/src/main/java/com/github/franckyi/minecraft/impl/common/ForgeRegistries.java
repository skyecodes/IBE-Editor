package com.github.franckyi.minecraft.impl.common;

import com.github.franckyi.minecraft.api.common.Enchantment;
import com.github.franckyi.minecraft.api.common.Registries;
import com.github.franckyi.minecraft.api.common.world.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ForgeRegistries implements Registries {
    public static final ForgeRegistries INSTANCE = new ForgeRegistries();

    private List<Enchantment> enchantments;

    @Override
    public List<Enchantment> getEnchantments() {
        if (enchantments == null) {
            enchantments = net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS.getEntries().stream().map(entry -> new Enchantment() {
                @Override
                public String getId() {
                    return entry.getKey().location().toString();
                }

                @Override
                public boolean isCurse() {
                    return entry.getValue().isCurse();
                }

                @Override
                public boolean canApply(Item item) {
                    return entry.getValue().canEnchant(item.get());
                }

                @Override
                public String getName() {
                    return entry.getValue().getDescriptionId();
                }
            }).collect(Collectors.toList());
        }
        return enchantments;
    }
}
