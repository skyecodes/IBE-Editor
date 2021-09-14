package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ILongTag;
import net.minecraft.nbt.LongTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LongTag.class)
public abstract class ForgeLongNBTMixin implements ILongTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract long getAsLong();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public long getValue() {
        return getAsLong();
    }
}
