package com.github.franckyi.ibeeditor.api.client.mvc.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;

public interface NBTEditorView extends View {
    Label getHeaderLabel();

    TreeView<TagModel> getTagTree();

    Button getDoneButton();

    Button getCancelButton();
}
