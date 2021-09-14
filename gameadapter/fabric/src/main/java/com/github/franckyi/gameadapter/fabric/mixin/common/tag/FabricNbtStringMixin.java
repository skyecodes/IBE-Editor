package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IStringTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;
import org.spongepowered.asm.mixin.*;

@Mixin(NbtString.class)
@Implements(@Interface(iface = IStringTag.class, prefix = "proxy$"))
public abstract class FabricNbtStringMixin implements NbtElement {
    @Shadow
    public abstract String asString();

    public String proxy$getValue() {
        return asString();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
    }
}
