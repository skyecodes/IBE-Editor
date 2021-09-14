package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.api.GuapiHelper.CENTER;
import static com.github.franckyi.guapi.api.GuapiHelper.label;

public class CategoryView implements View {
    private Label root;

    @Override
    public void build() {
        root = label().textAlign(CENTER);
    }

    public Label getLabel() {
        return root;
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
