package com.github.franckyi.ibeeditor.api.client.mvc.base.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;

public interface ListEditorView extends View {
    ListView<EditorCategoryModel> getCategoryList();

    ListView<EditorEntryModel> getEntryList();

    Button getCancelButton();

    Button getDoneButton();
}
