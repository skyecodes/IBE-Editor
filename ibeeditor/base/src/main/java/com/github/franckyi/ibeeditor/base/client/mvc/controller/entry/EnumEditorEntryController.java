package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.EnumEditorEntryView;

public class EnumEditorEntryController<E extends Enum<E>> extends ValueEditorEntryController<EnumEditorEntryModel<E>, EnumEditorEntryView<E>> {
    public EnumEditorEntryController(EnumEditorEntryModel<E> model, EnumEditorEntryView<E> view) {
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
