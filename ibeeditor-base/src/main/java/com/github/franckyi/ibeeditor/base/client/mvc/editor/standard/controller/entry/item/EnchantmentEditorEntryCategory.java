package com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.controller.entry.item;

import com.github.franckyi.ibeeditor.base.client.mvc.base.controller.entry.IntegerEditorEntryController;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model.entry.item.EnchantmentEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.view.entry.item.EnchantmentEditorEntryView;

public class EnchantmentEditorEntryCategory extends IntegerEditorEntryController<EnchantmentEditorEntryModel, EnchantmentEditorEntryView> {
    public EnchantmentEditorEntryCategory(EnchantmentEditorEntryModel model, EnchantmentEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getPlusButton().onAction(() -> model.setValue(model.getValue() + 1));
        view.getMinusButton().onAction(() -> model.setValue(model.getValue() - 1));
    }
}
