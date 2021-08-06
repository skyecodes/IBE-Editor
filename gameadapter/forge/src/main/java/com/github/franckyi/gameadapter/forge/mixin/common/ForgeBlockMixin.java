package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class ForgeBlockMixin extends AbstractBlock implements IItemProvider, IForgeBlock, IRegistryValue {
    @Shadow
    public abstract String getDescriptionId();

    public ForgeBlockMixin(Properties p_i241196_1_) {
        super(p_i241196_1_);
    }

    @Override
    public String getName() {
        return getDescriptionId();
    }
}
