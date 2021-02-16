package com.github.franckyi.ibeeditor.api.client.mvc.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;

public interface StringEntryView extends View {
    Label getLabel();

    TextField getTextField();

    Button getResetButton();
}
