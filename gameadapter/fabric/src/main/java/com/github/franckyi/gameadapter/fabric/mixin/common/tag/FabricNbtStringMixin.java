package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.IStringTag;
import net.minecraft.nbt.NbtString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NbtString.class)
public abstract class FabricNbtStringMixin implements IStringTag {
    @Shadow
    public abstract String asString();

    @Override
    public String getValue() {
        return asString();
    }
}
