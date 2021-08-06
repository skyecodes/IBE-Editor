package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IByteArrayTag;
import net.minecraft.nbt.NbtByteArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NbtByteArray.class)
public abstract class FabricNbtByteArrayMixin implements IByteArrayTag {
    @Shadow
    public abstract byte[] getByteArray();

    @Override
    public byte[] getValue() {
        return getByteArray();
    }
}
