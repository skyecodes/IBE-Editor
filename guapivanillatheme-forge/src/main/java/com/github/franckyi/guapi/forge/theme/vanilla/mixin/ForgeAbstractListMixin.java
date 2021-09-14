package com.github.franckyi.guapi.forge.theme.vanilla.mixin;

import com.github.franckyi.guapi.api.node.ListNode;
import com.github.franckyi.guapi.forge.theme.vanilla.AbstractForgeVanillaListNodeRenderer;
import net.minecraft.client.gui.widget.list.AbstractList;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractList.class)
public abstract class ForgeAbstractListMixin<E> {
    @Shadow
    protected int y0;

    @Shadow
    protected int headerHeight;

    @Shadow
    public abstract double getScrollAmount();

    @Shadow
    @Final
    protected int itemHeight;

    @Shadow
    protected abstract int getScrollbarPosition();

    @Shadow
    protected abstract int getItemCount();

    @Shadow
    public abstract List<E> children();

    @Inject(method = "getEntryAtPosition(DD)Lnet/minecraft/client/gui/widget/list/AbstractList$AbstractListEntry;", at = @At("HEAD"), cancellable = true)
    private void getEntryAtPositionFix(double x, double y, CallbackInfoReturnable<E> cir) {
        if (!AbstractForgeVanillaListNodeRenderer.class.isInstance(this)) {
            return;
        }
        ListNode<?> node = AbstractForgeVanillaListNodeRenderer.class.cast(this).getNode();
        int k = node.getLeft();
        int l = node.getRight();
        int m = MathHelper.floor(y - (double) y0) - this.headerHeight + (int) getScrollAmount() - 4;
        int n = m / itemHeight;
        cir.setReturnValue(x < (double) getScrollbarPosition() && x >= (double) k && x <= (double) l && n >= 0 && m >= 0 && n < getItemCount() ? children().get(n) : null);
    }
}
