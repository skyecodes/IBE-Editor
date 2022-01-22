package com.github.franckyi.ibeeditor.client.screen.controller.entry;

import com.github.franckyi.ibeeditor.client.screen.model.entry.NumberEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.NumberEntryView;

public class NumberEntryController<N extends Number> extends ValueEntryController<NumberEntryModel<N>, NumberEntryView> {
    public NumberEntryController(NumberEntryModel<N> model, NumberEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setValidator(getModel().getTextPredicate().and(s -> getModel().validate(getModel().getToNumberFunction().apply(s))));
        view.getTextField().textProperty().addListener(value -> {
            if (view.getTextField().isValid()) {
                model.setValue(getModel().getToNumberFunction().apply(value));
                model.setValid(true);
            }
        });
        model.valueProperty().addListener(value -> view.getTextField().setText(model.getToStringFunction().apply(value)));
        view.getTextField().setText(model.getToStringFunction().apply(model.getValue()));
        view.getTextField().validProperty().addListener(model::setValid);
    }
}
