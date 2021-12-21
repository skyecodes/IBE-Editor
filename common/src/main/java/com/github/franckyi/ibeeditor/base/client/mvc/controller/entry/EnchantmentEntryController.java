package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnchantmentEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.EnchantmentEntryView;

public class EnchantmentEntryController extends SelectionEntryController<EnchantmentEntryModel, EnchantmentEntryView> {
    public EnchantmentEntryController(EnchantmentEntryModel model, EnchantmentEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getPlusButton().onAction(() -> model.levelProperty().incr());
        view.getMinusButton().onAction(() -> model.levelProperty().decr());
        view.getLevelField().setValidator(Predicates.range(0, 256));
        view.getLevelField().textProperty().addListener(value -> {
            if (view.getLevelField().isValid()) {
                int level = Integer.parseInt(value);
                model.setLevel(level);
                model.setValid(true);
                view.getPlusButton().setDisable(level == 255);
                view.getMinusButton().setDisable(level == 0);
            } else {
                view.getPlusButton().setDisable(true);
                view.getMinusButton().setDisable(true);
            }
        });
        view.getLevelField().setText(Integer.toString(model.getLevel()));
        model.levelProperty().addListener(value -> view.getLevelField().setText(Integer.toString(value)));
    }
}
