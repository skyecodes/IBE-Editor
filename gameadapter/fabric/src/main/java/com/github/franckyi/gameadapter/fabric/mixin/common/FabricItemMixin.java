package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class FabricItemMixin implements ItemConvertible, IRegistryValue {
    @Shadow
    public abstract String getTranslationKey();

    @Override
    public String getName() {
        return getTranslationKey();
    }
}
