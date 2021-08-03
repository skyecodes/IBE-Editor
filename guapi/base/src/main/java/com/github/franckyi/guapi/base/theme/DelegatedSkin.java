package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.api.util.ScreenEventType;

public abstract class DelegatedSkin<N extends Node> extends AbstractSkin<N> {
    private final DelegatedRenderer delegatedRenderer;

    public DelegatedSkin(DelegatedRenderer delegatedRenderer) {
        this.delegatedRenderer = delegatedRenderer;
    }

    @Override
    public boolean preRender(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        return getRendererDelegate().preRender(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void render(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        getRendererDelegate().render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void postRender(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.postRender(node, matrices, mouseX, mouseY, delta);
        getRendererDelegate().postRender(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void doTick() {
        delegatedRenderer.doTick();
    }

    @Override
    public <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event) {
        super.onEvent(type, event);
        type.onEvent(delegatedRenderer, event);
    }

    protected DelegatedRenderer getRendererDelegate() {
        return delegatedRenderer;
    }
}
