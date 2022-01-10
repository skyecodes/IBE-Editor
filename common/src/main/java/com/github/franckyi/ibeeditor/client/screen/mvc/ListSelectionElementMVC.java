package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.client.screen.controller.selection.element.*;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.*;
import com.github.franckyi.ibeeditor.client.screen.view.selection.element.ImageListSelectionElementView;
import com.github.franckyi.ibeeditor.client.screen.view.selection.element.ItemListSelectionElementView;
import com.github.franckyi.ibeeditor.client.screen.view.selection.element.ListSelectionElementView;
import com.github.franckyi.ibeeditor.client.screen.view.selection.element.SpriteListSelectionElementView;

public final class ListSelectionElementMVC implements MVC<ListSelectionElementModel, ListSelectionElementView, ListSelectionElementController<ListSelectionElementModel, ListSelectionElementView>> {
    public static final ListSelectionElementMVC INSTANCE = new ListSelectionElementMVC();

    private ListSelectionElementMVC() {
    }

    @Override
    public ListSelectionElementView setup(ListSelectionElementModel model) {
        return switch (model.getType()) {
            case DEFAULT -> MVC.createViewAndBind(model, ListSelectionElementView::new, ListSelectionElementController::new);
            case ITEM -> MVC.createViewAndBind((ItemListSelectionElementModel) model, ItemListSelectionElementView::new, ItemListSelectionElementController::new);
            case IMAGE -> MVC.createViewAndBind((ImageListSelectionElementModel) model, ImageListSelectionElementView::new, ImageListSelectionElementController::new);
            case SPRITE -> MVC.createViewAndBind((SpriteListSelectionElementModel) model, SpriteListSelectionElementView::new, SpriteListSelectionElementController::new);
            case ENCHANTMENT -> MVC.createViewAndBind((SortedEnchantmentListSelectionElementModel) model, ItemListSelectionElementView::new, SortedEnchantmentListSelectionElementController::new);
        };
    }
}
