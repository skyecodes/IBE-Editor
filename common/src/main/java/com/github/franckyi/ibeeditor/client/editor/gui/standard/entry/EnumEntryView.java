package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class EnumEntryView<E> extends LabeledEntryView {
    private EnumButton<E> button;

    @Override
    protected Node createLabeledContent() {
        return button = enumButton();
    }

    public EnumButton<E> getButton() {
        return button;
    }
}
