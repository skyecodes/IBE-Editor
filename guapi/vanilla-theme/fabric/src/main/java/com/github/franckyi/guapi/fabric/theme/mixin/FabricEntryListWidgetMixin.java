package com.github.franckyi.guapi.fabric.theme.mixin;

import com.github.franckyi.guapi.api.node.ListNode;
import com.github.franckyi.guapi.fabric.theme.vanilla.AbstractFabricVanillaListNodeRenderer;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EntryListWidget.class)
public abstract class FabricEntryListWidgetMixin<E> {
    @Shadow
    protected int top;

    @Shadow
    protected int headerHeight;

    @Shadow
    public abstract double getScrollAmount();

    @Shadow
    @Final
    protected int itemHeight;

    @Shadow
    protected abstract int getScrollbarPositionX();

    @Shadow
    protected abstract int getEntryCount();

    @Shadow
    public abstract List<E> children();

    @Inject(method = "getEntryAtPosition(DD)Lnet/minecraft/client/gui/widget/EntryListWidget$Entry;", at = @At("HEAD"), cancellable = true)
    private void getEntryAtPositionFix(double x, double y, CallbackInfoReturnable<E> cir) {
        if (!AbstractFabricVanillaListNodeRenderer.class.isInstance(this)) {
            return;
        }
        ListNode<?> node = AbstractFabricVanillaListNodeRenderer.class.cast(this).getNode();
        int k = node.getLeft();
        int l = node.getRight();
        int m = MathHelper.floor(y - (double) top) - this.headerHeight + (int) getScrollAmount() - 4;
        int n = m / itemHeight;
        cir.setReturnValue(x < (double) getScrollbarPositionX() && x >= (double) k && x <= (double) l && n >= 0 && m >= 0 && n < getEntryCount() ? children().get(n) : null);
    }
}
