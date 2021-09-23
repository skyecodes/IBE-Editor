package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.event.*;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.base.theme.SuppliedSkin;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaWidgetSkinDelegate;
import com.mojang.blaze3d.vertex.PoseStack;

public abstract class AbstractVanillaWidgetSkin<N extends Node, W extends VanillaWidgetSkinDelegate> extends SuppliedSkin<N> {
    private final W widget;

    protected AbstractVanillaWidgetSkin(N node, W widget) {
        super(node);
        this.widget = widget;
    }

    @Override
    public boolean preRender(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        boolean res = super.preRender(node, matrices, mouseX, mouseY, delta);
        res |= widget.preRender(matrices, mouseX, mouseY, delta);
        return res;
    }

    @Override
    public void render(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        widget.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void postRender(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.postRender(node, matrices, mouseX, mouseY, delta);
        widget.postRender(matrices, mouseX, mouseY, delta);
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
