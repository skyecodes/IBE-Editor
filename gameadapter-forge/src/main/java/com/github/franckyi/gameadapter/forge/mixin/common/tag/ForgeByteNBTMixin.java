package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IByteTag;
import net.minecraft.nbt.ByteTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ByteTag.class)
public abstract class ForgeByteNBTMixin implements IByteTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract byte getAsByte();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public byte getValue() {
        return getAsByte();
    }
}
