package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ListSelectionScreenController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ListSelectionScreenView;

public final class ListSelectionScreenMVC extends SimpleMVC<ListSelectionScreenModel, ListSelectionScreenView, ListSelectionScreenController> {
    public static final ListSelectionScreenMVC INSTANCE = new ListSelectionScreenMVC();

    private ListSelectionScreenMVC() {
        super(ListSelectionScreenView::new, ListSelectionScreenController::new);
    }
}
