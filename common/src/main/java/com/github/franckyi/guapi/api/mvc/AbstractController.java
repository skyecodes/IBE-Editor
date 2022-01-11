package com.github.franckyi.guapi.api.mvc;

public abstract class AbstractController<M, V extends View> implements Controller<M, V> {
    protected final M model;
    protected final V view;

    public AbstractController(M model, V view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public M getModel() {
        return model;
    }

    @Override
    public V getView() {
        return view;
    }
}
