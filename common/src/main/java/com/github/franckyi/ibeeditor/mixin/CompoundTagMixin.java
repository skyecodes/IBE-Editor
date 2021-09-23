package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(CompoundTag.class)
public interface CompoundTagMixin {
    @Invoker("entries")
    Map<String, Tag> getTags();
}
