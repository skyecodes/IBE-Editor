package com.github.franckyi.gameadapter.forge.mixin.client;

import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(AbstractContainerScreen.class)
public abstract class ForgeContainerScreenMixin<T extends AbstractContainerMenu> extends Screen implements MenuAccess<T>, IScreen {
    protected ForgeContainerScreenMixin(Component title) {
        super(title);
    }

    @Shadow(remap = false)
    @Nullable
    public abstract Slot getSlotUnderMouse();

    @Override
    public ISlot getInventoryFocusedSlot() {
        return (ISlot) getSlotUnderMouse();
    }

    @Override
    public boolean isCreativeInventoryScreen() {
        return CreativeModeInventoryScreen.class.isInstance(this);
    }
}
