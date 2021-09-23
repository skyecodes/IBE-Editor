package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.api.GuapiHelper.enumButton;

public class EnumEntryView<E extends Enum<E>> extends LabeledEntryView {
    private final Class<E> enumClass;
    private EnumButton<E> button;

    public EnumEntryView(Class<E> enumClass) {
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
