package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.*;

public final class EntryMVC implements MVC<EntryModel, EntryView, EntryController<EntryModel, EntryView>> {
    public static final EntryMVC INSTANCE = new EntryMVC();

    @Override
    public EntryView setup(EntryModel model) {
        switch (model.getType()) {
            case STRING:
                return MVC.createViewAndBind((StringEntryModel) model, StringEntryView::new, StringEntryController::new);
            case INTEGER:
                return MVC.createViewAndBind((IntegerEntryModel) model, IntegerEntryView::new, IntegerEntryController::new);
            case TEXT:
                return MVC.createViewAndBind((TextEntryModel) model, TextEntryView::new, TextEntryController::new);
            case ENUM:
                return createEnumViewAndBind((EnumEntryModel<?>) model);
            case ACTION:
                return MVC.createViewAndBind((ActionEntryModel) model, ActionEntryView::new, ActionEntryController::new);
            case ADD_LIST_ENTRY:
                return MVC.createViewAndBind((AddListEntryEntryModel) model, AddListEntryEntryView::new, AddListEntryEntryController::new);
            case BOOLEAN:
                return MVC.createViewAndBind((BooleanEntryModel) model, BooleanEntryView::new, BooleanEntryController::new);
            case ENCHANTMENT:
                return MVC.createViewAndBind((EnchantmentEntryModel) model, EnchantmentEntryView::new, EnchantmentEntryController::new);
            case HIDE_FLAG:
                return MVC.createViewAndBind((HideFlagEntryModel) model, HideFlagEntryView::new, HideFlagEntryController::new);
            case ATTRIBUTE_MODIFIER:
                return MVC.createViewAndBind((AttributeModifierEntryModel) model, AttributeModifierEntryView::new, AttributeModifierEntryController::new);
            default:
                throw new IllegalStateException("Unexpected value: " + model.getType());
        }
    }

    private <E extends Enum<E>> EntryView createEnumViewAndBind(EnumEntryModel<E> model) {
        return MVC.createViewAndBind(model, () -> new EnumEntryView<>(model.getValue().getDeclaringClass()), EnumEntryController::new);
    }
}
