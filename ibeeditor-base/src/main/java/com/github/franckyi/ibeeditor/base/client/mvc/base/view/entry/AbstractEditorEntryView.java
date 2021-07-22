package com.github.franckyi.ibeeditor.base.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractEditorEntryView implements EditorEntryView {
    private HBox root, right, buttons, listButtons;
    private Node content;
    private TexturedButton upButton, downButton, deleteButton, resetButton;

    @Override
    public void build() {
        listButtons = hBox(listButtons -> {
            listButtons.add(upButton = texturedButton("ibeeditor:textures/gui/move_up.png", 16, 16, false)
                    .tooltip(translated("ibeeditor.gui.move_up")));
            listButtons.add(downButton = texturedButton("ibeeditor:textures/gui/move_down.png", 16, 16, false)
                    .tooltip(translated("ibeeditor.gui.move_down")));
            listButtons.add(deleteButton = texturedButton("ibeeditor:textures/gui/remove.png", 16, 16, false)
                    .tooltip(translated("ibeeditor.gui.remove").red()));
            listButtons.spacing(2);
        });
        root = hBox(root -> {
            root.add(content = createContent(), 1);
            root.add(right = hBox(right -> {
                right.add(buttons = hBox(buttons -> {
                    buttons.add(resetButton = texturedButton("ibeeditor:textures/gui/reset.png", 16, 16, false)
                            .tooltip(translated("ibeeditor.gui.reset").yellow()));
                    buttons.spacing(2);
                }));
                right.spacing(10).align(CENTER_RIGHT);
            }));
            root.spacing(10).weight(content, 1).align(CENTER);
        });
    }

    protected abstract Node createContent();

    @Override
    public void setListButtonsVisible(boolean visible) {
        if (visible && buttons.getChildren().size() <= 1) {
            buttons.getChildren().add(0, listButtons);
        } else if (!visible && buttons.getChildren().size() > 1) {
            buttons.getChildren().remove(0);
        }
    }

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
