package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.gamehooks.util.common.tag.Tag;
import com.github.franckyi.guapi.api.node.*;
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
    public void updateIconFromTag(Tag<?> tag) {
        if (tag != null) {
            String id = "ibeeditor:textures/gui/";
            switch (tag.getType()) {
                case Tag.BYTE_ID:
                    id += "byte_tag";
                    break;
                case Tag.SHORT_ID:
                    id += "short_tag";
                    break;
                case Tag.INT_ID:
                    id += "int_tag";
                    break;
                case Tag.LONG_ID:
                    id += "long_tag";
                    break;
                case Tag.FLOAT_ID:
                    id += "float_tag";
                    break;
                case Tag.DOUBLE_ID:
                    id += "double_tag";
                    break;
                case Tag.BYTE_ARRAY_ID:
                    id += "byte_array_tag";
                    break;
                case Tag.STRING_ID:
                    id += "string_tag";
                    break;
                case Tag.LIST_ID:
                    id += "list_tag";
                    break;
                case Tag.COMPOUND_ID:
                    id += "object_tag";
                    break;
                case Tag.INT_ARRAY_ID:
                    id += "int_array_tag";
                    break;
                case Tag.LONG_ARRAY_ID:
                    id += "long_array_tag";
                    break;
            }
            id += ".png";
            icon.setTextureId(id);
        } else {
            icon.setVisible(false);
            icon.setPrefWidth(0);
        }
    }
}
