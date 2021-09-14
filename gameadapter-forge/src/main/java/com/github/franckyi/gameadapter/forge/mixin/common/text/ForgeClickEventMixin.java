package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.ITextEvent;
import net.minecraft.util.text.event.ClickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClickEvent.class)
public abstract class ForgeClickEventMixin implements ITextEvent {
    @Shadow
    public abstract ClickEvent.Action shadow$getAction();

    @Override
    public String getAction() {
        return shadow$getAction().getName();
    }
}
