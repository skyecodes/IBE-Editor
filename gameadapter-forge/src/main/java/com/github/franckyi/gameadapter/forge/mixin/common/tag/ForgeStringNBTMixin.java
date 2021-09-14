package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IStringTag;
import net.minecraft.nbt.StringTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StringTag.class)
public abstract class ForgeStringNBTMixin implements IStringTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract String getAsString();

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public String getValue() {
        return getAsString();
    }
}
