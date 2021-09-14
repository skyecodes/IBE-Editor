package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IByteArrayTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.NbtByteArray;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.*;

@Mixin(NbtByteArray.class)
@Implements(@Interface(iface = IByteArrayTag.class, prefix = "proxy$"))
public abstract class FabricNbtByteArrayMixin implements NbtElement {
    @Shadow
    public abstract byte[] getByteArray();

    public byte[] proxy$getValue() {
        return getByteArray();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
    }
}
