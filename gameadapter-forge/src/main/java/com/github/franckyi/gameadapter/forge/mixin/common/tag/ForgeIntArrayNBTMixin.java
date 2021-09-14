package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IIntArrayTag;
import net.minecraft.nbt.IntArrayTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(IntArrayTag.class)
public abstract class ForgeIntArrayNBTMixin implements IIntArrayTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract int[] getAsIntArray();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public int[] getValue() {
        return getAsIntArray();
    }
}
