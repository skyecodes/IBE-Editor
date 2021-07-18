package com.github.franckyi.ibeeditor.api.client.mvc.base.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.TexturedButton;

public interface EditorEntryView extends View {
    void setListButtonsVisible(boolean visible);

    TexturedButton getUpButton();

    TexturedButton getDownButton();

    TexturedButton getDeleteButton();

    TexturedButton getResetButton();
}
