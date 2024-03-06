package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreativeModeInventoryScreen.class)
public interface CreativeModeInventoryScreenMixin {
    @Accessor("selectedTab")
    CreativeModeTab getSelectedTab();
}
