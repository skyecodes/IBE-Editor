package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IDoubleTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtDouble;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtDouble.class)
@Implements(@Interface(iface = IDoubleTag.class, prefix = "proxy$"))
public abstract class FabricNbtDoubleMixin extends AbstractNbtNumber {
    public double proxy$getValue() {
        return doubleValue();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
    }
}
