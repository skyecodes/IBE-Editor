package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.api.client.mvc.view.IntegerEntryView;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class IntegerEntryViewImpl implements IntegerEntryView {
    private final HBox root;
    private Label label;
    private TextField textField;

    public IntegerEntryViewImpl() {
        root = hBox(root -> {
            root.add(label = label(), 1);
            root.add(textField = textField(), 2);
            root.spacing(10).align(CENTER);
        });
    }

    @Override
    public Label getLabel() {
        return label;
    }

    @Override
    public TextField getTextField() {
        return textField;
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
