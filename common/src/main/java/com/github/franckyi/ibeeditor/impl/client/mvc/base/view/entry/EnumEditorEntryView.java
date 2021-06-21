package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.EnumButton;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class EnumEditorEntryView<E extends Enum<E>> extends LabeledEditorEntryView {
    protected final EnumButton<E> button;

    public EnumEditorEntryView(Class<E> enumClass) {
        root.getChildren().add(button = enumButton(enumClass));
        root.setWeight(button, 2);
    }

    public EnumButton<E> getButton() {
        return button;
    }
}
