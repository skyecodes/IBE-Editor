package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class EnumEditorEntryView<E extends Enum<E>> extends LabeledEditorEntryView {
    private final Class<E> enumClass;
    private EnumButton<E> button;

    public EnumEditorEntryView(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    protected Node createLabeledContent() {
        return button = enumButton(enumClass);
    }

    public EnumButton<E> getButton() {
        return button;
    }
}
