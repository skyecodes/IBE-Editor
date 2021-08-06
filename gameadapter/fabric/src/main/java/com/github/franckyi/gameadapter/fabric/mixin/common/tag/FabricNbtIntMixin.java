package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IIntTag;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtInt;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtInt.class)
public abstract class FabricNbtIntMixin extends AbstractNbtNumber implements IIntTag {
    @Override
    public int getValue() {
        return intValue();
    }
}
