package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ILongTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtLong;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtLong.class)
public abstract class FabricNbtLongMixin extends AbstractNbtNumber implements ILongTag {
    @Override
    public long getValue() {
        return longValue();
    }
}
