package com.github.franckyi.ibeeditor.impl.client.mvc.base.view;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorCategoryView;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class EditorCategoryViewImpl implements EditorCategoryView {
    private final Label root;

    public EditorCategoryViewImpl() {
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
