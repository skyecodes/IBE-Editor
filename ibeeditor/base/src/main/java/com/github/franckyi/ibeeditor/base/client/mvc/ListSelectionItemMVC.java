package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.*;
import com.github.franckyi.ibeeditor.base.client.mvc.model.*;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ImageListSelectionItemView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemListSelectionItemView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ListSelectionItemView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.SpriteListSelectionItemView;

public final class ListSelectionItemMVC implements MVC<ListSelectionItemModel, ListSelectionItemView, ListSelectionItemController<ListSelectionItemModel, ListSelectionItemView>> {
    public static final ListSelectionItemMVC INSTANCE = new ListSelectionItemMVC();

    private ListSelectionItemMVC() {
    }

    @Override
    public ListSelectionItemView setup(ListSelectionItemModel model) {
        switch (model.getType()) {
            case DEFAULT:
                return MVC.createViewAndBind(model, ListSelectionItemView::new, ListSelectionItemController::new);
            case ITEM:
                return MVC.createViewAndBind(((ItemListSelectionItemModel) model), ItemListSelectionItemView::new, ItemListSelectionItemController::new);
            case IMAGE:
                return MVC.createViewAndBind(((ImageListSelectionItemModel) model), ImageListSelectionItemView::new, ImageListSelectionItemController::new);
            case SPRITE:
                return MVC.createViewAndBind((SpriteListSelectionItemModel) model, SpriteListSelectionItemView::new, SpriteListSelectionItemController::new);
            case ENCHANTMENT:
                return MVC.createViewAndBind((SortedEnchantmentListSelectionItemModel) model, ItemListSelectionItemView::new, SortedEnchantmentListSelectionItemController::new);
            default:
                return null;
        }
    }
}
