package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import net.minecraft.nbt.*;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NbtList.class)
@Implements(@Interface(iface = IListTag.class, prefix = "list$"))
public abstract class FabricNbtListMixin extends AbstractNbtList<NbtElement> {
    @Shadow
    public abstract NbtCompound getCompound(int index);

    public ICompoundTag list$getCompound(int index) {
        return (ICompoundTag) getCompound(index);
    }

    public void list$addString(String string) {
        add(NbtString.of(string));
    }

    public void list$addTag(ITag tag) {
        add((NbtElement) tag);
    }
}
