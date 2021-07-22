package com.github.franckyi.ibeeditor.base.client.mvc.editor.nbt.view;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view.EditorTagView;

import java.util.function.Predicate;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class EditorTagViewImpl implements EditorTagView {
    private final String texture;
    private final Text tooltip;
    private Predicate<String> validator;
    private HBox root;
    private TextField nameField;
    private Label separator;
    private TextField valueField;

    public EditorTagViewImpl(String texture, Text tooltip, Predicate<String> validator) {
        this(texture, tooltip);
        this.validator = validator;
    }

    public EditorTagViewImpl(String texture, Text tooltip) {
        this.texture = texture;
        this.tooltip = tooltip;
    }

    @Override
    public void build() {
        root = hBox(root -> {
            root.add(imageView(String.format("ibeeditor:textures/gui/%s.png", texture), 16, 16).tooltip(tooltip));
            root.add(nameField = textField().prefHeight(14).prefWidth(120));
            root.add(separator = label(":"));
            root.add(valueField = textField().prefHeight(14));
            root.spacing(5).align(CENTER_LEFT);
        });
        if (validator != null) {
            valueField.setValidator(validator);
        }
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
