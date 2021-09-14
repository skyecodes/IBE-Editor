package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IIntTag;
import net.minecraft.nbt.IntTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(IntTag.class)
public abstract class ForgeIntNBTMixin implements IIntTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract int getAsInt();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public int getValue() {
        return getAsInt();
    }
}
