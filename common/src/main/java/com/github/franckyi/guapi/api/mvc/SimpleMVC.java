package com.github.franckyi.guapi.api.mvc;

public interface SimpleMVC<M, V extends View, C extends Controller<M, V>> extends MVC<M, V, C> {
    @Override
    default V createViewAndBind(M model) {
        V view = createView();
        C controller = createController(model, view);
        controller.bind();
        return view;
    }

    V createView();

    C createController(M model, V view);
}
