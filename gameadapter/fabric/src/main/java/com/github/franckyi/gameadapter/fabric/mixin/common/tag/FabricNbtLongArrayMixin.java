package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ILongArrayTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtLongArray;
import org.spongepowered.asm.mixin.*;

@Mixin(NbtLongArray.class)
@Implements(@Interface(iface = ILongArrayTag.class, prefix = "proxy$"))
public abstract class FabricNbtLongArrayMixin implements NbtElement {
    @Shadow
    public abstract long[] getLongArray();

    public long[] proxy$getValue() {
        return getLongArray();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
    }
}
