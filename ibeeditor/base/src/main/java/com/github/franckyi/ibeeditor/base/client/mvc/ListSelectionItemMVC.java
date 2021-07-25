package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ItemListSelectionItemController;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ListSelectionItemController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemListSelectionItemView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ListSelectionItemView;

public final class ListSelectionItemMVC implements MVC<ListSelectionItemModel, ListSelectionItemView, ListSelectionItemController<ListSelectionItemModel, ListSelectionItemView>> {
    public static final ListSelectionItemMVC INSTANCE = new ListSelectionItemMVC();

    private ListSelectionItemMVC() {
    }

    @Override
    public ListSelectionItemView setup(ListSelectionItemModel model) {
        if (model instanceof ItemListSelectionItemModel) {
            return MVC.createViewAndBind(((ItemListSelectionItemModel) model), ItemListSelectionItemView::new, ItemListSelectionItemController::new);
        }
        return MVC.createViewAndBind(model, ListSelectionItemView::new, ListSelectionItemController::new);
    }
}
