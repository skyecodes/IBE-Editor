package com.github.franckyi.guapi.api.mvc;

public interface Controller<M, V extends View> {
    void init(M model, V view);
}
