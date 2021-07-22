package com.github.franckyi.ibeeditor.base.client.mvc.base;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.base.EditorEntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;
import com.github.franckyi.ibeeditor.base.client.mvc.base.controller.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.base.model.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.base.view.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.controller.entry.item.EnchantmentEditorEntryCategory;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model.entry.item.EnchantmentEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.view.entry.item.EnchantmentEditorEntryView;

public class EditorEntryMVCImpl implements EditorEntryMVC {
    public static final EditorEntryMVC INSTANCE = new EditorEntryMVCImpl();

    @Override
    public EditorEntryView setup(EditorEntryModel model) {
        switch (model.getType()) {
            case STRING:
                return MVC.createViewAndBind((StringEditorEntryModel) model, StringEditorEntryView::new, StringEditorEntryController::new);
            case INTEGER:
                return MVC.createViewAndBind((IntegerEditorEntryModel) model, IntegerEditorEntryView::new, IntegerEditorEntryController::new);
            case TEXT:
                return MVC.createViewAndBind((TextEditorEntryModel) model, TextEditorEntryView::new, TextEditorEntryController::new);
            case ENUM:
                return createEnumViewAndBind((EnumEditorEntryModel<?>) model);
            case ACTION:
                return MVC.createViewAndBind((ActionEditorEntryModel) model, ActionEditorEntryView::new, ActionEditorEntryController::new);
            case ADD_LIST_ENTRY:
                return MVC.createViewAndBind((AddListEntryEditorEntryModel) model, AddListEntryEditorEntryView::new, AddListEntryEditorEntryController::new);
            case BOOLEAN:
                return MVC.createViewAndBind((BooleanEditorEntryModel) model, BooleanEditorEntryView::new, BooleanEditorEntryController::new);
            case ENCHANTMENT:
                return MVC.createViewAndBind((EnchantmentEditorEntryModel) model, EnchantmentEditorEntryView::new, EnchantmentEditorEntryCategory::new);
            default:
                throw new IllegalStateException("Unexpected value: " + model.getType());
        }
    }

    private <E extends Enum<E>> EditorEntryView createEnumViewAndBind(EnumEditorEntryModel<E> model) {
        return MVC.createViewAndBind(model, () -> new EnumEditorEntryView<>(model.getValue().getDeclaringClass()), EnumEditorEntryController::new);
    }
}
