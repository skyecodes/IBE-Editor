package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IDoubleTag;
import net.minecraft.nbt.DoubleNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DoubleNBT.class)
public abstract class ForgeDoubleNBTMixin implements IDoubleTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract double getAsDouble();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public double getValue() {
        return getAsDouble();
    }
}
