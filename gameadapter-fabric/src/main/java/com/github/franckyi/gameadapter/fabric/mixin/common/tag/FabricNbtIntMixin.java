package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IIntTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtInt;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtInt.class)
@Implements(@Interface(iface = IIntTag.class, prefix = "proxy$"))
public abstract class FabricNbtIntMixin extends AbstractNbtNumber {
    public int proxy$getValue() {
        return intValue();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }
}
