package com.github.franckyi.ibeeditor.api.client.mvc.base.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;

public interface EditorView extends View {
    Button getCancelButton();

    Button getDoneButton();
}
