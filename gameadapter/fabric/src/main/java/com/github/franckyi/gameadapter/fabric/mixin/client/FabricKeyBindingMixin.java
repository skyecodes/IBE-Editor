package com.github.franckyi.gameadapter.fabric.mixin.client;

import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KeyBinding.class)
public abstract class FabricKeyBindingMixin implements Comparable<KeyBinding>, IKeyBinding {
    @Override
    public int getKeyCode() {
        return KeyBindingHelper.getBoundKeyOf(KeyBinding.class.cast(this)).getCode();
    }
}
