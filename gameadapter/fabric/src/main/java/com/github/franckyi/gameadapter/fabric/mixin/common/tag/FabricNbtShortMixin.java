package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IShortTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtShort;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtShort.class)
@Implements(@Interface(iface = IShortTag.class, prefix = "proxy$"))
public abstract class FabricNbtShortMixin extends AbstractNbtNumber {
    public short proxy$getValue() {
        return shortValue();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
    }
}
