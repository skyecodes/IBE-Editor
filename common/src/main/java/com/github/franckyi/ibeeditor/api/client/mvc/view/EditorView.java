package com.github.franckyi.ibeeditor.api.client.mvc.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.VBox;
import com.github.franckyi.ibeeditor.api.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EntryModel;

public interface EditorView extends View<VBox> {
    Label getHeaderLabel();

    ListView<CategoryModel> getCategoryList();

    ListView<EntryModel> getEntryList();

    Button getDoneButton();

    Button getCancelButton();
}
