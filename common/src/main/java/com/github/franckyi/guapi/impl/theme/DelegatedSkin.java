package com.github.franckyi.guapi.impl.theme;

import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.util.ScreenEventType;

public abstract class DelegatedSkin<N extends Node> extends AbstractSkin<N> {
    private final DelegatedRenderer<?> delegatedRenderer;

    public DelegatedSkin(DelegatedRenderer<?> delegatedRenderer) {
        this.delegatedRenderer = delegatedRenderer;
    }

    @Override
    public <M> boolean preRender(N node, M matrices, int mouseX, int mouseY, float delta) {
        return preRenderDelegate(matrices, mouseX, mouseY, delta);
    }

    @Override
    public <M> void render(N node, M matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderDelegate(matrices, mouseX, mouseY, delta);
    }

    @Override
    public <M> void postRender(N node, M matrices, int mouseX, int mouseY, float delta) {
        super.postRender(node, matrices, mouseX, mouseY, delta);
        postRenderDelegate(matrices, mouseX, mouseY, delta);
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

    protected <M> boolean preRenderDelegate(M matrices, int mouseX, int mouseY, float delta) {
        return this.<M>getRendererDelegate().preRender(matrices, mouseX, mouseY, delta);
    }

    protected <M> void renderDelegate(M matrices, int mouseX, int mouseY, float delta) {
        this.<M>getRendererDelegate().render(matrices, mouseX, mouseY, delta);
    }

    protected <M> void postRenderDelegate(M matrices, int mouseX, int mouseY, float delta) {
        this.<M>getRendererDelegate().postRender(matrices, mouseX, mouseY, delta);
    }

    @SuppressWarnings("unchecked")
    protected <M> DelegatedRenderer<M> getRendererDelegate() {
        return (DelegatedRenderer<M>) delegatedRenderer;
    }
}
