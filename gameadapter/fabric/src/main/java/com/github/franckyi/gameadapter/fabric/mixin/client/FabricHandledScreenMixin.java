package com.github.franckyi.gameadapter.fabric.mixin.client;

import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.gameadapter.api.common.ISlot;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HandledScreen.class)
public abstract class FabricHandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T>, IScreen {
    @Shadow
    @Nullable
    protected Slot focusedSlot;

    protected FabricHandledScreenMixin(Text title) {
        super(title);
    }

    @Override
    public ISlot getInventoryFocusedSlot() {
        return (ISlot) focusedSlot;
    }

    @Override
    public boolean isCreativeInventoryScreen() {
        return CreativeInventoryScreen.class.isInstance(this);
    }
}
