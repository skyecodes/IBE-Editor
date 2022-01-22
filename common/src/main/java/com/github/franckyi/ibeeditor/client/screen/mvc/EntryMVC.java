package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.*;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.item.*;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.vault.VaultEntityEntryController;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.vault.VaultItemEntryController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.*;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.*;
import com.github.franckyi.ibeeditor.client.screen.model.entry.vault.VaultEntityEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.vault.VaultItemEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.*;
import com.github.franckyi.ibeeditor.client.screen.view.entry.item.*;
import com.github.franckyi.ibeeditor.client.screen.view.entry.vault.VaultEntityEntryView;
import com.github.franckyi.ibeeditor.client.screen.view.entry.vault.VaultItemEntryView;

import java.util.function.Supplier;

public final class EntryMVC implements MVC<EntryModel, EntryView, EntryController<EntryModel, EntryView>> {
    public static final EntryMVC INSTANCE = new EntryMVC();

    @Override
    public EntryView setup(EntryModel model) {
        return switch (model.getType()) {
            case STRING -> MVC.createViewAndBind((StringEntryModel) model, StringEntryView::new, StringEntryController::new);
            case NUMBER -> MVC.createViewAndBind((NumberEntryModel<?>) model, NumberEntryView::new, NumberEntryController::new);
            case TEXT -> MVC.createViewAndBind((TextEntryModel) model, TextEntryView::new, TextEntryController::new);
            case ENUM -> createEnumViewAndBind((EnumEntryModel<?>) model);
            case ACTION -> MVC.createViewAndBind((ActionEntryModel) model, ActionEntryView::new, ActionEntryController::new);
            case ADD_LIST_ENTRY -> MVC.createViewAndBind((AddListEntryEntryModel) model, AddListEntryEntryView::new, AddListEntryEntryController::new);
            case BOOLEAN -> MVC.createViewAndBind((BooleanEntryModel) model, BooleanEntryView::new, BooleanEntryController::new);
            case ENCHANTMENT -> MVC.createViewAndBind((EnchantmentEntryModel) model, EnchantmentEntryView::new, EnchantmentEntryController::new);
            case HIDE_FLAG -> MVC.createViewAndBind((HideFlagEntryModel) model, HideFlagEntryView::new, HideFlagEntryController::new);
            case ATTRIBUTE_MODIFIER -> MVC.createViewAndBind((AttributeModifierEntryModel) model, AttributeModifierEntryView::new, AttributeModifierEntryController::new);
            case SELECTION -> MVC.createViewAndBind((SelectionEntryModel) model, SelectionEntryView::new, SelectionEntryController::new);
            case SELECTION_POTION -> MVC.createViewAndBind(((PotionSelectionEntryModel) model), PotionSelectionEntryView::new, PotionSelectionEntryController::new);
            case POTION_EFFECT -> MVC.createViewAndBind(((PotionEffectEntryModel) model), PotionEffectEntryView::new, PotionEffectEntryController::new);
            case ARMOR_COLOR -> MVC.createViewAndBind((ArmorColorEntryModel) model, ArmorColorEntryView::new, ArmorColorEntryController::new);
            case VAULT_ITEM -> MVC.createViewAndBind((VaultItemEntryModel) model, VaultItemEntryView::new, VaultItemEntryController::new);
            case VAULT_ENTITY -> MVC.createViewAndBind((VaultEntityEntryModel) model, VaultEntityEntryView::new, VaultEntityEntryController::new);
        };
    }

    private <E> EntryView createEnumViewAndBind(EnumEntryModel<E> model) {
        return MVC.createViewAndBind(model, (Supplier<EnumEntryView<E>>) EnumEntryView::new, EnumEntryController::new);
    }
}
