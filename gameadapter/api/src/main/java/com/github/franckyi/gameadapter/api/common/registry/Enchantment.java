package com.github.franckyi.gameadapter.api.common.registry;

import com.github.franckyi.gameadapter.api.common.world.Item;

import java.util.function.Predicate;

public interface Enchantment extends RegistryEntry {
    boolean isCurse();

    boolean canApply(Item item);

    static Enchantment of(String id, String name, boolean isCurse, Predicate<Item> canApply) {
        return new Enchantment() {
            @Override
            public boolean isCurse() {
                return isCurse;
            }

            @Override
            public boolean canApply(Item item) {
                return canApply.test(item);
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }
        };
    }
}
