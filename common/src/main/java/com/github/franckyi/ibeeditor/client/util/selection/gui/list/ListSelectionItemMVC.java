package com.github.franckyi.ibeeditor.client.util.selection.gui.list;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.item.*;

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
            default:
                return null;
        }
    }
}
