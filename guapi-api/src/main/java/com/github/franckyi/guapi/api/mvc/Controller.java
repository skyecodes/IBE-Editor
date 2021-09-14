package com.github.franckyi.guapi.api.mvc;

public interface Controller<M, V extends View> {
    M getModel();

    V getView();

    void bind();
}
