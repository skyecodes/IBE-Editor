package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.EnumEntryView;

public class EnumEntryController<E extends Enum<E>> extends LabeledEntryController<EnumEntryModel<E>, EnumEntryView<E>> {
    public EnumEntryController(EnumEntryModel<E> model, EnumEntryView<E> view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getButton().setValue(model.getValue());
        view.getButton().valueProperty().bindBidirectional(model.valueProperty());
        model.setValid(true);
    }
}
