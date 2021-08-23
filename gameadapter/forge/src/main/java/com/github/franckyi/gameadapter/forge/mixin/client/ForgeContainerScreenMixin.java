package com.github.franckyi.gameadapter.forge.mixin.client;

import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(ContainerScreen.class)
public abstract class ForgeContainerScreenMixin<T extends Container> extends Screen implements IHasContainer<T>, IScreen {
    protected ForgeContainerScreenMixin(ITextComponent title) {
        super(title);
    }

    @Shadow
    @Nullable
    public abstract Slot getSlotUnderMouse();

    @Override
    public ISlot getInventoryFocusedSlot() {
        return (ISlot) getSlotUnderMouse();
    }

    @Override
    public boolean isCreativeInventoryScreen() {
        return CreativeScreen.class.isInstance(this);
    }
}
