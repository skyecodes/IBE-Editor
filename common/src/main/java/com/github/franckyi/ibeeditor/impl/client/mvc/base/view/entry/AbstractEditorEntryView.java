package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class AbstractEditorEntryView implements EditorEntryView {
    private HBox root;
    private Node content;
    private HBox right;
    private HBox listButtons;
    private TexturedButton upButton, downButton, deleteButton, resetButton;

    @Override
    public void build() {
        root = hBox(10,
                content = createContent(),
                right = hBox(2,
                        listButtons = hBox(2,
                                upButton = texturedButton("ibeeditor:textures/gui/move_up.png", 16, 16, false),
                                downButton = texturedButton("ibeeditor:textures/gui/move_down.png", 16, 16, false),
                                deleteButton = texturedButton("ibeeditor:textures/gui/delete.png", 16, 16, false)
                        ).visible(false),
                        resetButton = texturedButton("ibeeditor:textures/gui/reset.png", 16, 16, false)
                ).align(CENTER_RIGHT)
        ).weight(content, 1).align(CENTER);
    }

    protected abstract Node createContent();

    protected Node getContent() {
        return content;
    }

    protected HBox getRight() {
        return right;
    }

    @Override
    public final HBox getRoot() {
        return root;
    }

    @Override
    public HBox getListButtons() {
        return listButtons;
    }

    @Override
    public TexturedButton getUpButton() {
        return upButton;
    }

    @Override
    public TexturedButton getDownButton() {
        return downButton;
    }

    @Override
    public TexturedButton getDeleteButton() {
        return deleteButton;
    }

    @Override
    public TexturedButton getResetButton() {
        return resetButton;
    }
}
