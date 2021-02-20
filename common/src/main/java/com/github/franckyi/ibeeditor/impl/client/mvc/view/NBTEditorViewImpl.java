package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;
import com.github.franckyi.ibeeditor.api.client.mvc.view.TagView;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class NBTEditorViewImpl implements NBTEditorView {
    private final VBox root;
    private Label headerLabel;
    private TreeView<TagModel> tagTree;
    private Button doneButton;
    private Button cancelButton;

    public NBTEditorViewImpl() {
        root = vBox(root -> {
            root.spacing(5).align(CENTER).padding(5).fillWidth();
            root.add(headerLabel = label(translated("ibeeditor.gui.nbt_editor", AQUA, BOLD), true).textAlign(CENTER).prefHeight(20));
            root.add(hBox(main -> {
                main.add(tagTree = treeView(TagModel.class, left -> left.itemHeight(20).padding(5).childrenIncrement(20).renderer(item -> mvc(TagView.class, item))), 1);
                main.spacing(10).fillHeight();
            }), 1);
            root.add(hBox(footer -> {
                footer.spacing(20).align(CENTER);
                footer.add(doneButton = button(translated("gui.done", GREEN)).prefWidth(90));
                footer.add(cancelButton = button(translated("gui.cancel", RED)).prefWidth(90));
            }));
        });
    }

    @Override
    public Label getHeaderLabel() {
        return headerLabel;
    }

    @Override
    public TreeView<TagModel> getTagTree() {
        return tagTree;
    }

    @Override
    public Button getDoneButton() {
        return doneButton;
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
