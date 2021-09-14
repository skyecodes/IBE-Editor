package com.github.franckyi.guapi.api.mvc;

import com.github.franckyi.databindings.api.Property;

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

    protected <T> void softBind(Property<T> a, Property<T> b) {
        a.addListener(b::set);
        b.addListener(a::set);
    }
}
