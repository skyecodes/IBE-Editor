package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnchantmentEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.EnchantmentEntryView;

public class EnchantmentEntryController extends IntegerEntryController<EnchantmentEntryModel, EnchantmentEntryView> {
    public EnchantmentEntryController(EnchantmentEntryModel model, EnchantmentEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getPlusButton().onAction(() -> model.setValue(model.getValue() + 1));
        view.getMinusButton().onAction(() -> model.setValue(model.getValue() - 1));
    }
}
