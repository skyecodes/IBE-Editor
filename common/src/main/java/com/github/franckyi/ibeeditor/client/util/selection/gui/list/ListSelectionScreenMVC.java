package com.github.franckyi.ibeeditor.client.util.selection.gui.list;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;

public final class ListSelectionScreenMVC extends SimpleMVC<ListSelectionScreenModel, ListSelectionScreenView, ListSelectionScreenController> {
    public static final ListSelectionScreenMVC INSTANCE = new ListSelectionScreenMVC();

    private ListSelectionScreenMVC() {
        super(ListSelectionScreenView::new, ListSelectionScreenController::new);
    }
}
