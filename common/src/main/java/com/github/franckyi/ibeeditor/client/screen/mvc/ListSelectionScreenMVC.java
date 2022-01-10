package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.selection.ListSelectionScreenController;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ListSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.ListSelectionScreenView;

public final class ListSelectionScreenMVC extends SimpleMVC<ListSelectionScreenModel, ListSelectionScreenView, ListSelectionScreenController> {
    public static final ListSelectionScreenMVC INSTANCE = new ListSelectionScreenMVC();

    private ListSelectionScreenMVC() {
        super(ListSelectionScreenView::new, ListSelectionScreenController::new);
    }
}
