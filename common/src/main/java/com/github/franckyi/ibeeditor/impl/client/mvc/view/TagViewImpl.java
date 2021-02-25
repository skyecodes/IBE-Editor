package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.gamehooks.util.common.tag.Tag;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.ImageView;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.util.Predicates;
import com.github.franckyi.ibeeditor.api.client.mvc.view.TagView;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class TagViewImpl implements TagView {
    private final HBox root;
    private ImageView icon;
    private TextField nameField;
    private Label separator;
    private TextField valueField;

    public TagViewImpl() {
        root = hBox(root -> {
            root.add(icon = imageView("", 16, 16));
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
    public ImageView getIcon() {
        return icon;
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

    @Override
    public void updateFromTagType(byte tagType) {
        String id = "ibeeditor:textures/gui/";
        Text tooltip = null;
        switch (tagType) {
            case Tag.BYTE_ID:
                id += "byte_tag";
                tooltip = text("Byte", BLUE);
                valueField.setValidator(Predicates.IS_BYTE);
                break;
            case Tag.SHORT_ID:
                id += "short_tag";
                tooltip = text("Short", GREEN);
                valueField.setValidator(Predicates.IS_SHORT);
                break;
            case Tag.INT_ID:
                id += "int_tag";
                tooltip = text("Int", AQUA);
                valueField.setValidator(Predicates.IS_INT);
                break;
            case Tag.LONG_ID:
                id += "long_tag";
                tooltip = text("Long", RED);
                valueField.setValidator(Predicates.IS_LONG);
                break;
            case Tag.FLOAT_ID:
                id += "float_tag";
                tooltip = text("Float", LIGHT_PURPLE);
                valueField.setValidator(Predicates.IS_FLOAT);
                break;
            case Tag.DOUBLE_ID:
                id += "double_tag";
                tooltip = text("Double", YELLOW);
                valueField.setValidator(Predicates.IS_DOUBLE);
                break;
            case Tag.BYTE_ARRAY_ID:
                id += "byte_array_tag";
                tooltip = text("Byte Array", BLUE);
                break;
            case Tag.STRING_ID:
                id += "string_tag";
                tooltip = text("String", GRAY);
                break;
            case Tag.LIST_ID:
                id += "list_tag";
                tooltip = text("List", GREEN);
                break;
            case Tag.COMPOUND_ID:
                id += "object_tag";
                tooltip = text("Compound", LIGHT_PURPLE);
                break;
            case Tag.INT_ARRAY_ID:
                id += "int_array_tag";
                tooltip = text("Int Array", AQUA);
                break;
            case Tag.LONG_ARRAY_ID:
                id += "long_array_tag";
                tooltip = text("Long Array", RED);
                break;
        }
        id += ".png";
        icon.setTextureId(id);
        icon.setTooltip(tooltip);
    }
}
