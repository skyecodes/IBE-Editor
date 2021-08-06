package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IIntArrayTag;
import net.minecraft.nbt.NbtIntArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NbtIntArray.class)
public abstract class FabricNbtIntArrayMixin implements IIntArrayTag {
    @Shadow
    public abstract int[] getIntArray();

    @Override
    public int[] getValue() {
        return getIntArray();
    }
}
