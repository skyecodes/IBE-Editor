package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ItemSelectionItemController;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.SelectionItemController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemSelectionItemView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.SelectionItemView;

public final class SelectionItemMVC implements MVC<SelectionItemModel, SelectionItemView, SelectionItemController<SelectionItemModel, SelectionItemView>> {
    public static final SelectionItemMVC INSTANCE = new SelectionItemMVC();

    private SelectionItemMVC() {
    }

    @Override
    public SelectionItemView setup(SelectionItemModel model) {
        if (model instanceof ItemSelectionItemModel) {
            return MVC.createViewAndBind(((ItemSelectionItemModel) model), ItemSelectionItemView::new, ItemSelectionItemController::new);
        }
        return MVC.createViewAndBind(model, SelectionItemView::new, SelectionItemController::new);
    }
}
