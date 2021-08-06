package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import net.minecraft.nbt.*;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ListNBT.class)
@Implements(@Interface(iface = IListTag.class, prefix = "list$"))
public abstract class ForgeListNBTMixin extends CollectionNBT<INBT> {
    @Shadow
    public abstract CompoundNBT getCompound(int index);

    public byte list$getType() {
        return getId();
    }

    public ICompoundTag list$getCompound(int index) {
        return (ICompoundTag) getCompound(index);
    }

    public void list$addString(String string) {
        add(StringNBT.valueOf(string));
    }

    public void list$addTag(ITag tag) {
        add((INBT) tag);
    }
}
