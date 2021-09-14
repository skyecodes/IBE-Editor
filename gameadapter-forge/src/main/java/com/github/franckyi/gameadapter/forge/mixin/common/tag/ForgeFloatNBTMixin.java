package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IFloatTag;
import net.minecraft.nbt.FloatNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FloatNBT.class)
public abstract class ForgeFloatNBTMixin implements IFloatTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract float getAsFloat();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public float getValue() {
        return getAsFloat();
    }
}
