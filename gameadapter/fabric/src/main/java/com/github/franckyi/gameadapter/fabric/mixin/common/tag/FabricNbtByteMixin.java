package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IByteTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtByte;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtByte.class)
@Implements(@Interface(iface = IByteTag.class, prefix = "proxy$"))
public abstract class FabricNbtByteMixin extends AbstractNbtNumber {
    public byte proxy$getValue() {
        return byteValue();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
    }
}
