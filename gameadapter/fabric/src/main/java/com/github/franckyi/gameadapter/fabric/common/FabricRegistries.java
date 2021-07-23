package com.github.franckyi.gameadapter.fabric.common;

import com.github.franckyi.gameadapter.api.common.Attribute;
import com.github.franckyi.gameadapter.api.common.Enchantment;
import com.github.franckyi.gameadapter.api.common.Registries;
import com.github.franckyi.gameadapter.api.common.world.Item;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.stream.Collectors;

public final class FabricRegistries implements Registries {
    public static final FabricRegistries INSTANCE = new FabricRegistries();
    private List<Enchantment> enchantments;
    private List<Attribute> attributes;
    private List<String> attributeSuggestions;

    private FabricRegistries() {
    }

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
        attributes = Registry.ATTRIBUTE.getEntries().stream().map(entry -> new Attribute() {
            @Override
            public String getId() {
                return entry.getKey().getValue().toString();
            }

            @Override
            public String getName() {
                return entry.getValue().getTranslationKey();
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
