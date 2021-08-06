package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IFloatTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtFloat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtFloat.class)
public abstract class FabricNbtFloatMixin extends AbstractNbtNumber implements IFloatTag {
    @Override
    public float getValue() {
        return floatValue();
    }
}
