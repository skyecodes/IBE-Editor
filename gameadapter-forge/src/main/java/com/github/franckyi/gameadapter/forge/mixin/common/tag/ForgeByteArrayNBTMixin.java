package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IByteArrayTag;
import net.minecraft.nbt.ByteArrayTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ByteArrayTag.class)
public abstract class ForgeByteArrayNBTMixin implements IByteArrayTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract byte[] getAsByteArray();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public byte[] getValue() {
        return getAsByteArray();
    }
}
