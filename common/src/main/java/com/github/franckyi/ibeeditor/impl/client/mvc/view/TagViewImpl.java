package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.api.client.mvc.view.TagView;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class TagViewImpl implements TagView {
    private final HBox root;
    private TextField nameField;
    private Label separator;
    private TextField valueField;

    public TagViewImpl() {
        root = hBox(root -> {
            root.add(nameField = textField().prefHeight(14).prefWidth(80));
            root.add(separator = label("="));
            root.add(valueField = textField().prefHeight(14));
            root.spacing(5).align(CENTER_LEFT);
        });
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public TextField getNameField() {
        return nameField;
    }

    @Override
    public Label getSeparator() {
        return separator;
    }

    @Override
    public TextField getValueField() {
        return valueField;
    }
}
