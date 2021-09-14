package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IIntArrayTag;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIntArray;
import org.spongepowered.asm.mixin.*;

@Mixin(NbtIntArray.class)
@Implements(@Interface(iface = IIntArrayTag.class, prefix = "proxy$"))
public abstract class FabricNbtIntArrayMixin implements NbtElement {
    @Shadow
    public abstract int[] getIntArray();

    public int[] proxy$getValue() {
        return getIntArray();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }
}
