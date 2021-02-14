package com.github.franckyi.guapi;

import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Node;

import java.util.HashMap;
import java.util.Map;

public class GUAPIMVC {
    private static final Map<Class<?>, MVC<?, ?, ?>> mvcMap = new HashMap<>();

    public static <M, V extends View<?>, C extends Controller<M, V>> void register(Class<M> modelClass, MVC<M, V, C> mvc) {
        mvcMap.put(modelClass, mvc);
    }

    @SuppressWarnings("unchecked")
    public static <M, V extends View<?>, C extends Controller<M, V>> Node load(Class<M> modelClass, M model) {
        MVC<M, V, C> mvc = (MVC<M, V, C>) mvcMap.get(modelClass);
        V view = mvc.createView();
        C controller = mvc.getController();
        controller.init(model, view);
        return view.getRoot();
    }
}
