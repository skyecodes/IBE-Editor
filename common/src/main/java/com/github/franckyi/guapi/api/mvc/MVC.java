package com.github.franckyi.guapi.api.mvc;

public interface MVC<M, V extends View, C extends Controller<M, V>> {
    V createViewAndBind(M model);
}
