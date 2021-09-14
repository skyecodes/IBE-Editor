package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class ForgeBlockMixin extends BlockBehaviour implements ItemLike, IForgeBlock, IRegistryValue {
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
