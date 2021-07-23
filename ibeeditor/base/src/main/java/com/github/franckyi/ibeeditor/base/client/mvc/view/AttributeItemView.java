package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.VBox;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class AttributeItemView implements View {
    private VBox root;
    private Label nameLabel;
    private Label idLabel;

    @Override
    public void build() {
        root = vBox(root -> {
            root.add(nameLabel = label());
            root.add(idLabel = label());
            root.fillWidth().spacing(2).align(CENTER);
        });
    }

    @Override
    public Node getRoot() {
        return root;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getIdLabel() {
        return idLabel;
    }
}
