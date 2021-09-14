package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import net.minecraft.nbt.*;
import org.spongepowered.asm.mixin.*;

@Mixin(NbtList.class)
@Implements(@Interface(iface = IListTag.class, prefix = "proxy$"))
public abstract class FabricNbtListMixin extends AbstractNbtList<NbtElement> {
    @Shadow
    public abstract NbtCompound getCompound(int index);

    @Shadow
    public abstract String getString(int index);

    @Intrinsic
    public String proxy$getString(int index) {
        return getString(index);
    }

    public ICompoundTag proxy$getCompound(int index) {
        return (ICompoundTag) getCompound(index);
    }

    public void proxy$addString(String string) {
        add(NbtString.of(string));
    }

    public void proxy$addTag(ITag tag) {
        add((NbtElement) tag);
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }
}
