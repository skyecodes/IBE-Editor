package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.guapi.api.node.ListNode;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.AbstractVanillaListNodeSkinDelegate;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractSelectionList.class)
public abstract class AbstractSelectionListMixin<E> {
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

    @Inject(method = "getEntryAtPosition", at = @At("HEAD"), cancellable = true)
    private void getEntryAtPositionFix(double x, double y, CallbackInfoReturnable<E> cir) {
        if (!AbstractVanillaListNodeSkinDelegate.class.isInstance(this)) {
            return;
        }
        ListNode<?> node = AbstractVanillaListNodeSkinDelegate.class.cast(this).getNode();
        int k = node.getLeft();
        int l = node.getRight();
        int m = Mth.floor(y - (double) node.getY()) - this.headerHeight + (int) getScrollAmount() - 4;
        int n = m / itemHeight;
        cir.setReturnValue(x < (double) getScrollbarPosition() && x >= (double) k && x <= (double) l && n >= 0 && m >= 0 && n < getItemCount() ? children().get(n) : null);
    }
}
