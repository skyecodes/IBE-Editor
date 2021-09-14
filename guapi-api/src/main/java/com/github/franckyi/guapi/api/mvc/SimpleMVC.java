package com.github.franckyi.guapi.api.mvc;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public abstract class SimpleMVC<M extends Model, V extends View, C extends Controller<M, V>> implements MVC<M, V, C> {
    private final Supplier<V> viewSupplier;
    private final BiFunction<M, V, C> controllerFactory;

    protected SimpleMVC(Supplier<V> viewSupplier, BiFunction<M, V, C> controllerFactory) {
        this.viewSupplier = viewSupplier;
        this.controllerFactory = controllerFactory;
    }

    @Override
    public V setup(M model) {
        return MVC.createViewAndBind(model, viewSupplier, controllerFactory);
    }
}
