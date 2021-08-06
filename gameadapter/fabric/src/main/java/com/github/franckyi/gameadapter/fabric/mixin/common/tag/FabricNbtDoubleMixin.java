package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IDoubleTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtDouble;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtDouble.class)
public abstract class FabricNbtDoubleMixin extends AbstractNbtNumber implements IDoubleTag {
    @Override
    public double getValue() {
        return doubleValue();
    }
}
