package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ForgeItemMixin extends ForgeRegistryEntry<Item> implements ItemLike, IForgeItem, IRegistryValue {
    @Shadow
    public abstract String getDescriptionId();

    @Override
    public String getName() {
        return getDescriptionId();
    }
}
