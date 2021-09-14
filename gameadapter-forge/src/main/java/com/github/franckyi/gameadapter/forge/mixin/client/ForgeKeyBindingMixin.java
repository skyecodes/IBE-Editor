package com.github.franckyi.gameadapter.forge.mixin.client;

import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.extensions.IForgeKeybinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBinding.class)
public abstract class ForgeKeyBindingMixin implements Comparable<KeyBinding>, IForgeKeybinding, IKeyBinding {
    @Shadow
    public abstract boolean consumeClick();

    @Override
    public boolean wasPressed() {
        return consumeClick();
    }

    @Override
    public int getKeyCode() {
        return getKey().getValue();
    }
}
