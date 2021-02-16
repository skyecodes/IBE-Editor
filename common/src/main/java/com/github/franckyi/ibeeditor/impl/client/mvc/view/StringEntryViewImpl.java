package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.ibeeditor.api.client.mvc.view.StringEntryView;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class StringEntryViewImpl implements StringEntryView {
    private final HBox root;
    private Label label;
    private TextField textField;
    private Button resetButton;

    public StringEntryViewImpl() {
        root = hBox(root -> {
            root.add(label = label().shadow(), 1);
            root.add(textField = textField(), 2);
            root.add(resetButton = button("<", DARK_RED, BOLD).prefWidth(20));
            root.spacing(5).align(CENTER);
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
    public Button getResetButton() {
        return resetButton;
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
