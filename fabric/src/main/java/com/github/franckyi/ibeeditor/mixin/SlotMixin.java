package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Slot.class)
public interface SlotMixin {
    @Accessor("index")
    int getIndex();
}
