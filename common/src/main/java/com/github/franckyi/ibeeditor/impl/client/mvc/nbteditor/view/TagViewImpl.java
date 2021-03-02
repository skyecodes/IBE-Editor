package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.view;

import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.TagView;

import java.util.function.Predicate;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class TagViewImpl implements TagView {
    private final HBox root;
    private TextField nameField;
    private Label separator;
    private TextField valueField;

    public TagViewImpl(String texture, Text tooltip, Predicate<String> validator) {
        this(texture, tooltip);
        valueField.setValidator(validator);
    }

    public TagViewImpl(String texture, Text tooltip) {
        root = hBox(root -> {
            root.add(imageView(String.format("ibeeditor:textures/gui/%s.png", texture), 16, 16).tooltip(tooltip));
            root.add(nameField = textField().prefHeight(14).prefWidth(120));
            root.add(separator = label(":"));
            root.add(valueField = textField().prefHeight(14));
            root.spacing(5).align(CENTER_LEFT);
        });
    }

    @Override
    public HBox getRoot() {
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
