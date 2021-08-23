package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ForgeItemMixin extends ForgeRegistryEntry<Item> implements IItemProvider, IForgeItem, IRegistryValue {
    @Shadow
    public abstract String getDescriptionId();

    @Override
    public String getName() {
        return getDescriptionId();
    }
}
