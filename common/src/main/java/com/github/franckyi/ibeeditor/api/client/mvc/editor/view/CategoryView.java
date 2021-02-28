package com.github.franckyi.ibeeditor.api.client.mvc.editor.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;

public interface CategoryView extends View {
    Label getLabel();

    @Override
    default Node getRoot() {
        return getLabel();
    }
}
