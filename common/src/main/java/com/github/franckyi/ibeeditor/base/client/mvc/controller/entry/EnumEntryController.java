package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.EnumEntryView;

public class EnumEntryController<E> extends ValueEntryController<EnumEntryModel<E>, EnumEntryView<E>> {
    public EnumEntryController(EnumEntryModel<E> model, EnumEntryView<E> view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getButton().getValues().setAll(model.getValues());
        view.getButton().valueProperty().addListener(model::setValue);
        model.valueProperty().addListener(view.getButton()::setValue);
        view.getButton().setValue(model.getValue());
    }
}
