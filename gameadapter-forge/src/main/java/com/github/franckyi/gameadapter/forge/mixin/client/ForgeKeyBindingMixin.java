package com.github.franckyi.gameadapter.forge.mixin.client;

import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.extensions.IForgeKeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyMapping.class)
public abstract class ForgeKeyBindingMixin implements Comparable<KeyMapping>, IForgeKeyMapping, IKeyBinding {
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
