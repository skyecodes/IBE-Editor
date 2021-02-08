package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.event.ScreenEvent;
import com.github.franckyi.guapi.event.ScreenEventType;
import com.github.franckyi.guapi.hooks.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.node.Node;

public abstract class DelegatedSkin<N extends Node> extends AbstractSkin<N> {
    private final DelegatedRenderer<?> delegatedRenderer;

    public DelegatedSkin(DelegatedRenderer<?> delegatedRenderer) {
        this.delegatedRenderer = delegatedRenderer;
    }

    @Override
    public void render(N node, Object matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderDelegate(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void tick() {
        delegatedRenderer.tick();
    }

    @Override
    public <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event) {
        super.onEvent(type, event);
        type.onEvent(delegatedRenderer, event);
    }

    protected <M> void renderDelegate(M matrices, int mouseX, int mouseY, float delta) {
        this.<M>getRendererDelegate().render(matrices, mouseX, mouseY, delta);
    }

    @SuppressWarnings("unchecked")
    protected <M> DelegatedRenderer<M> getRendererDelegate() {
        return (DelegatedRenderer<M>) delegatedRenderer;
    }
}
