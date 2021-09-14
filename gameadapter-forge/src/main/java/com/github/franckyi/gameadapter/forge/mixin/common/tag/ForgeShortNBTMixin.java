package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IShortTag;
import net.minecraft.nbt.ShortNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShortNBT.class)
public abstract class ForgeShortNBTMixin implements IShortTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract short getAsShort();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public short getValue() {
        return getAsShort();
    }
}
