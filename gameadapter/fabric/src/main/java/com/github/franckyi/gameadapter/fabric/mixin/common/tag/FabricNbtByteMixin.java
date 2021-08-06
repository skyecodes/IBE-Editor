package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IByteTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtByte;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtByte.class)
public abstract class FabricNbtByteMixin extends AbstractNbtNumber implements IByteTag {
    @Override
    public byte getValue() {
        return byteValue();
    }
}
