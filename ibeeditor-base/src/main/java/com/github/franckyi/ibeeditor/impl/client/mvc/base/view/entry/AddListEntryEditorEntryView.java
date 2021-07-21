package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.util.Align;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class AddListEntryEditorEntryView extends AbstractEditorEntryView {
    private TexturedButton button;

    @Override
    protected Node createContent() {
        return hBox(
                button = texturedButton("ibeeditor:textures/gui/add.png", 16, 16, false)
        ).align(Align.CENTER);
    }

    public TexturedButton getButton() {
        return button;
    }
}
