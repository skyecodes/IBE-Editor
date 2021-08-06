package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ILongArrayTag;
import net.minecraft.nbt.NbtLongArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NbtLongArray.class)
public abstract class FabricNbtLongArrayMixin implements ILongArrayTag {
    @Shadow
    public abstract long[] getLongArray();

    @Override
    public long[] getValue() {
        return getLongArray();
    }
}
