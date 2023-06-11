package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.event.*;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.base.theme.SuppliedSkin;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaWidgetSkinDelegate;
import net.minecraft.client.gui.GuiGraphics;

public abstract class AbstractVanillaWidgetSkin<N extends Node, W extends VanillaWidgetSkinDelegate> extends SuppliedSkin<N> {
    private final W widget;

    protected AbstractVanillaWidgetSkin(N node, W widget) {
        super(node);
        this.widget = widget;
    }

    @Override
    public boolean preRender(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        boolean res = super.preRender(node, guiGraphics, mouseX, mouseY, delta);
        res |= widget.preRender(guiGraphics, mouseX, mouseY, delta);
        return res;
    }

    @Override
    public void render(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(node, guiGraphics, mouseX, mouseY, delta);
        widget.render(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    public void postRender(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.postRender(node, guiGraphics, mouseX, mouseY, delta);
        widget.postRender(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(MouseButtonEvent event) {
        widget.mouseClicked(event);
        //widget.mouseClicked(event.getMouseX(), event.getMouseY(), event.getButton());
    }

    @Override
    public void mouseReleased(MouseButtonEvent event) {
        widget.mouseReleased(event);
        //widget.mouseReleased(event.getMouseX(), event.getMouseY(), event.getButton());
    }

    @Override
    public void mouseDragged(MouseDragEvent event) {
        widget.mouseDragged(event);
        //widget.mouseDragged(event.getMouseX(), event.getMouseY(), event.getButton(), event.getDeltaX(), event.getDeltaY());
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        widget.mouseScrolled(event);
        //widget.mouseScrolled(event.getMouseX(), event.getMouseY(), event.getAmount());
    }

    @Override
    public void keyPressed(KeyEvent event) {
        widget.keyPressed(event);
        //widget.keyPressed(event.getKeyCode(), event.getScanCode(), event.getModifiers());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        widget.keyReleased(event);
        //widget.keyReleased(event.getKeyCode(), event.getScanCode(), event.getModifiers());
    }

    @Override
    public void charTyped(TypeEvent event) {
        widget.charTyped(event);
        //widget.charTyped(event.getCharacter(), event.getModifiers());
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        widget.mouseMoved(event);
        //widget.mouseMoved(event.getMouseX(), event.getMouseY());
    }
}
