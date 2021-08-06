package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IShortTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtShort;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtShort.class)
public abstract class FabricNbtShortMixin extends AbstractNbtNumber implements IShortTag {
    @Override
    public short getValue() {
        return shortValue();
    }
}
