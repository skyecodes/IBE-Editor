package com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;

public interface EditorTagView extends View {
    @Override
    HBox getRoot();

    TextField getNameField();

    Label getSeparator();

    TextField getValueField();
}
