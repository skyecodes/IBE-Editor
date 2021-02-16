package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.api.client.mvc.view.CategoryView;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class CategoryViewImpl implements CategoryView {
    private final Label root;

    public CategoryViewImpl() {
        root = label().shadow().textAlign(CENTER);
    }

    @Override
    public Label getLabel() {
        return root;
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
