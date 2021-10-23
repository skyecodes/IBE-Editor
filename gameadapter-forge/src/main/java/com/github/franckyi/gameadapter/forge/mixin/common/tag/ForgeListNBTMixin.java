package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import net.minecraft.nbt.*;
import org.spongepowered.asm.mixin.*;

@Mixin(ListTag.class)
@Implements(@Interface(iface = IListTag.class, prefix = "proxy$"))
public abstract class ForgeListNBTMixin extends CollectionTag<Tag> {
    @Shadow
    public abstract String getString(int p_150307_1_);

    @Shadow
    public abstract CompoundTag getCompound(int index);

    public byte proxy$getType() {
        return getId();
    }

    @Intrinsic
    public String proxy$getString(int index) {
        return getString(index);
    }

    public ICompoundTag proxy$getCompound(int index) {
        return (ICompoundTag) getCompound(index);
    }

    public void proxy$addString(String string) {
        add(StringTag.valueOf(string));
    }

    public void proxy$addTag(ITag tag) {
        add((Tag) tag);
    }
}