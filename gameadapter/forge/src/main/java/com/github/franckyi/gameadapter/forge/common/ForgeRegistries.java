package com.github.franckyi.gameadapter.forge.common;

import com.github.franckyi.gameadapter.api.common.Attribute;
import com.github.franckyi.gameadapter.api.common.Enchantment;
import com.github.franckyi.gameadapter.api.common.Registries;
import com.github.franckyi.gameadapter.api.common.world.Item;

import java.util.List;
import java.util.stream.Collectors;

public final class ForgeRegistries implements Registries {
    public static final ForgeRegistries INSTANCE = new ForgeRegistries();
    private List<Enchantment> enchantments;
    private List<Attribute> attributes;
    private List<String> attributeSuggestions;

    private ForgeRegistries() {
    }

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

    @Override
    public List<Attribute> getAttributes() {
        if (attributes == null) {
            loadAttributes();
        }
        return attributes;
    }

    @Override
    public List<String> getAttributeSuggestions() {
        if (attributeSuggestions == null) {
            loadAttributes();
        }
        return attributeSuggestions;
    }

    private void loadAttributes() {
        attributes = net.minecraftforge.registries.ForgeRegistries.ATTRIBUTES.getEntries().stream().map(entry -> new Attribute() {
            @Override
            public String getId() {
                return entry.getKey().location().toString();
            }

            @Override
            public String getName() {
                return entry.getValue().getDescriptionId();
            }
        }).collect(Collectors.toList());
        attributeSuggestions = attributes.stream().map(Attribute::getId).collect(Collectors.toList());
        attributes.forEach(attribute -> {
            if (attribute.getId().startsWith("minecraft:")) {
                attributeSuggestions.add(attribute.getId().substring(10));
            }
        });
    }
}
