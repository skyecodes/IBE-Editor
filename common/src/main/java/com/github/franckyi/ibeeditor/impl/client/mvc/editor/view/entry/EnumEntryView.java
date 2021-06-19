package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry;

import com.github.franckyi.guapi.api.node.EnumButton;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class EnumEntryView<E extends Enum<E>> extends LabeledEntryView {
    protected final EnumButton<E> button;

    public EnumEntryView(Class<E> enumClass) {
        root.getChildren().add(button = enumButton(enumClass));
        root.setWeight(button, 2);
    }

    public EnumButton<E> getButton() {
        return button;
    }
}
