package com.github.franckyi.guapi.gui;

import com.github.franckyi.guapi.math.Insets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class GuiListExtendedView<E extends GuiListExtended.IGuiListEntry<E>> extends GuiListExtended<E> implements GuiView {

    private Insets offset;

    public GuiListExtendedView(int slotHeight) {
        super(Minecraft.getInstance(), 0, 0, 0, 0, slotHeight);
        offset = Insets.NONE;
    }

    public void setSlotHeight(int slotHeight) {
        ObfuscationReflectionHelper.setPrivateValue(GuiSlot.class, this, slotHeight, "field_148149_f");
    }

    public Insets getOffset() {
        return offset;
    }

    public void setOffset(Insets offset) {
        int x = this.getX();
        int y = this.getY();
        this.offset = offset;
        left = x + offset.getLeft();
        right = x + width - offset.getRight();
        top = y + offset.getTop();
        bottom = y + height - offset.getBottom();
    }

    @Override
    public int getX() {
        return left - offset.getLeft();
    }

    @Override
    public void setX(int x) {
        left = x + offset.getLeft();
        right = x + width - offset.getRight();
    }

    @Override
    public int getY() {
        return top - offset.getTop();
    }

    @Override
    public void setY(int y) {
        top = y + offset.getTop();
        bottom = y + height - offset.getBottom();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        right = left + width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        bottom = top + height;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int getListWidth() {
        return width;
    }

    @Override
    protected int getScrollBarX() {
        return right - 7;
    }

    @Override
    public void setHasListHeader(boolean hasListHeaderIn, int headerPaddingIn) {
        super.setHasListHeader(hasListHeaderIn, headerPaddingIn);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn) {
        drawCenteredString(mc.fontRenderer, "Header", insideLeft + width / 2 - offset.getRight(), insideTop, 0xffffff);
    }

    @Override
    public boolean keyReleased(int p_keyReleased_1_, int p_keyReleased_2_, int p_keyReleased_3_) {
        return super.keyReleased(p_keyReleased_1_, p_keyReleased_2_, p_keyReleased_3_);
    }

}
