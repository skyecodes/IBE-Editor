package com.github.franckyi.guapi.api.mvc;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface MVC<M extends Model, V extends View, C extends Controller<M, V>> {
    V setup(M model);

    static <M extends Model, V extends View, C extends Controller<M, V>> V createViewAndBind(M model, Supplier<V> viewSupplier, BiFunction<M, V, C> controllerFactory) {
        model.initalize();
        V view = viewSupplier.get();
        view.build();
        C controller = controllerFactory.apply(model, view);
        controller.bind();
        return view;
    }
}
