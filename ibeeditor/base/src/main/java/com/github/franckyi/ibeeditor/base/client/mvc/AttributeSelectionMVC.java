package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.AttributeSelectionController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.AttributeSelectionModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.AttributeSelectionView;

public final class AttributeSelectionMVC extends SimpleMVC<AttributeSelectionModel, AttributeSelectionView, AttributeSelectionController> {
    public static final AttributeSelectionMVC INSTANCE = new AttributeSelectionMVC();

    private AttributeSelectionMVC() {
        super(AttributeSelectionView::new, AttributeSelectionController::new);
    }
}
