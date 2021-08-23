package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.base.client.ModTextures;

import java.util.function.Predicate;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class NBTTagView implements View {
    private final String texture;
    private final IText tooltip;
    private Predicate<String> validator;
    private HBox root;
    private TextField nameField;
    private Label separator;
    private TextField valueField;

    public NBTTagView(String texture, IText tooltip, Predicate<String> validator) {
        this(texture, tooltip);
        this.validator = validator;
    }

    public NBTTagView(String texture, IText tooltip) {
        this.texture = texture;
        this.tooltip = tooltip;
    }

    @Override
    public void build() {
        root = hBox(root -> {
            root.add(imageView(ModTextures.gui(texture), 16, 16).tooltip(tooltip));
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

    public TextField getNameField() {
        return nameField;
    }

    public Label getSeparator() {
        return separator;
    }

    public TextField getValueField() {
        return valueField;
    }
}
