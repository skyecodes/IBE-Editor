package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IFloatTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtFloat;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtFloat.class)
@Implements(@Interface(iface = IFloatTag.class, prefix = "proxy$"))
public abstract class FabricNbtFloatMixin extends AbstractNbtNumber {
    public float proxy$getValue() {
        return floatValue();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }
}
