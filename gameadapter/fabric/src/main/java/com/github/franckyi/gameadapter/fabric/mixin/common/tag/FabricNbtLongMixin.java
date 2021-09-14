package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ILongTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtLong;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtLong.class)
@Implements(@Interface(iface = ILongTag.class, prefix = "proxy$"))
public abstract class FabricNbtLongMixin extends AbstractNbtNumber {
    public long proxy$getValue() {
        return longValue();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
    }
}
