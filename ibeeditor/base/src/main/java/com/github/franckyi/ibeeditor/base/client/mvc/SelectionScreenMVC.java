package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.SelectionScreenController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.SelectionScreenView;

public final class SelectionScreenMVC extends SimpleMVC<SelectionScreenModel, SelectionScreenView, SelectionScreenController> {
    public static final SelectionScreenMVC INSTANCE = new SelectionScreenMVC();

    private SelectionScreenMVC() {
        super(SelectionScreenView::new, SelectionScreenController::new);
    }
}
