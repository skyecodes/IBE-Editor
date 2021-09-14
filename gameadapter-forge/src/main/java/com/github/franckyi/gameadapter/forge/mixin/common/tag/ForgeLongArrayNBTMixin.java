package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ILongArrayTag;
import net.minecraft.nbt.LongArrayNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LongArrayNBT.class)
public abstract class ForgeLongArrayNBTMixin implements ILongArrayTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract long[] getAsLongArray();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public long[] getValue() {
        return getAsLongArray();
    }
}
