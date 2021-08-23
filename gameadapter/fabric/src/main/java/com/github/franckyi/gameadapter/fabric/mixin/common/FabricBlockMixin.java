package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class FabricBlockMixin extends AbstractBlock implements ItemConvertible, IRegistryValue {
    protected FabricBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    public abstract String getTranslationKey();

    @Override
    public String getName() {
        return getTranslationKey();
    }
}
