package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.*;

public class EditorEntryMVC implements MVC<EditorEntryModel, EditorEntryView, EditorEntryController<EditorEntryModel, EditorEntryView>> {
    public static final EditorEntryMVC INSTANCE = new EditorEntryMVC();

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
                return MVC.createViewAndBind((ItemEnchantmentEditorEntryModel) model, ItemEnchantmentEditorEntryView::new, ItemEnchantmentEditorEntryController::new);
            case HIDE_FLAG:
                return MVC.createViewAndBind((ItemHideFlagEditorEntryModel) model, ItemHideFlagEditorEntryView::new, ItemHideFlagEditorEntryController::new);
            default:
                throw new IllegalStateException("Unexpected value: " + model.getType());
        }
    }

    private <E extends Enum<E>> EditorEntryView createEnumViewAndBind(EnumEditorEntryModel<E> model) {
        return MVC.createViewAndBind(model, () -> new EnumEditorEntryView<>(model.getValue().getDeclaringClass()), EnumEditorEntryController::new);
    }
}
