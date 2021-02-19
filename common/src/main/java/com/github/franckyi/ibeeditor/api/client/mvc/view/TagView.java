package com.github.franckyi.ibeeditor.api.client.mvc.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;

public interface TagView extends View {
    TextField getNameField();

    Label getSeparator();

    TextField getValueField();
}
