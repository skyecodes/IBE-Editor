package com.github.franckyi.guapi.api.mvc;

public interface SimpleMVC<M extends Model, V extends View, C extends Controller<M, V>> extends MVC<M, V, C> {
    @Override
    default V setup(M model) {
        return MVC.createViewAndBind(model, this::createView, this::createController);
    }

    V createView();

    C createController(M model, V view);
}
