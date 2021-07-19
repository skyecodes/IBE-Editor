package com.github.franckyi.minecraft.impl.common;

import com.github.franckyi.minecraft.api.common.Enchantment;
import com.github.franckyi.minecraft.api.common.Registries;
import com.github.franckyi.minecraft.api.common.world.Item;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.stream.Collectors;

public class FabricRegistries implements Registries {
    public static final FabricRegistries INSTANCE = new FabricRegistries();

    private FabricRegistries() {
    }

    private List<Enchantment> enchantments;

    @Override
    public List<Enchantment> getEnchantments() {
        if (enchantments == null) {
            enchantments = Registry.ENCHANTMENT.getEntries().stream().map(entry -> new Enchantment() {
                @Override
                public String getId() {
                    return entry.getKey().getValue().toString();
                }

                @Override
                public boolean isCurse() {
                    return entry.getValue().isCursed();
                }

                @Override
                public boolean canApply(Item item) {
                    return entry.getValue().isAcceptableItem(item.get());
                }

                @Override
                public String getName() {
                    return entry.getValue().getTranslationKey();
                }
            }).collect(Collectors.toList());
        }
        return enchantments;
    }
}
