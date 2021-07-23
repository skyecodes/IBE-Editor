package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.AttributeItemController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.AttributeItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.AttributeItemView;

public final class AttributeItemMVC extends SimpleMVC<AttributeItemModel, AttributeItemView, AttributeItemController> {
    public static final AttributeItemMVC INSTANCE = new AttributeItemMVC();

    private AttributeItemMVC() {
        super(AttributeItemView::new, AttributeItemController::new);
    }
}
