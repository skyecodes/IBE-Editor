package com.github.franckyi.gameadapter.fabric.mixin.client;

import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.*;

@Mixin(KeyBinding.class)
@Implements(@Interface(iface = IKeyBinding.class, prefix = "proxy$"))
public abstract class FabricKeyBindingMixin implements Comparable<KeyBinding> {
    @Shadow
    public abstract boolean wasPressed();

    @Intrinsic
    public boolean proxy$wasPressed() {
        return wasPressed();
    }

    public int proxy$getKeyCode() {
        return KeyBindingHelper.getBoundKeyOf(KeyBinding.class.cast(this)).getCode();
    }
}
