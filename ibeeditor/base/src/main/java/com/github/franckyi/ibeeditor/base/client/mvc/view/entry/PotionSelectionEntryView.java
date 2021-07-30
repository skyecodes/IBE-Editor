package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Box;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.node.VBox;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class PotionSelectionEntryView extends SelectionEntryView {
    private VBox root;
    private TexturedButton resetPotionButton, resetColorButton;
    private TexturedButton chooseColorButton;
    private ItemView potionView;
    private TexturedButton removeColorButton;
    private final TexturedButton dummyUpButton, dummyDownButton, dummyDeleteButton;

    public PotionSelectionEntryView() {
        dummyUpButton = texturedButton(null, false);
        dummyDownButton = texturedButton(null, false);
        dummyDeleteButton = texturedButton(null, false);
    }

    @Override
    public void build() {
        root = vBox(root -> {
            root.add(hBox(potion -> {
                potion.add(createContent(), 1);
                potion.add(hBox(right -> {
                    right.add(createLabeledContent(), 1);
                    right.add(hBox(buttons -> {
                        buttons.add(resetPotionButton = texturedButton("ibeeditor:textures/gui/reset.png", 16, 16, false)
                                .tooltip(translated("ibeeditor.gui.reset").yellow()));
                        buttons.spacing(2);
                    }));
                    right.spacing(5).align(CENTER_RIGHT);
                }), 2);
                potion.fillHeight().spacing(5).align(CENTER);
            }), 1);
            root.add(hBox(color -> {
                color.add(label(translated("ibeeditor.gui.potion_color")).padding(right(5)).textAlign(CENTER_RIGHT), 1);
                color.add(hBox(right -> {
                    right.add(hBox(content -> {
                        content.add(chooseColorButton = texturedButton("ibeeditor:textures/gui/color_custom.png", 16, 16, false)
                                .tooltip(translated("ibeeditor.gui.choose_custom_color")));
                        content.add(potionView = itemView());
                        content.add(removeColorButton = texturedButton("ibeeditor:textures/gui/remove.png", 16, 16, false)
                                .tooltip(translated("ibeeditor.gui.remove_custom_color").red()));
                        content.spacing(5);
                    }), 1);
                    right.add(hBox(buttons -> {
                        buttons.add(resetColorButton = texturedButton("ibeeditor:textures/gui/reset.png", 16, 16, false)
                                .tooltip(translated("ibeeditor.gui.reset").yellow()));
                        buttons.spacing(2);
                    }));
                    right.spacing(5).align(CENTER_RIGHT);
                }), 2);
                color.fillHeight().spacing(5).align(CENTER);
            }), 1);
            root.spacing(5).fillWidth();
        });
    }

    @Override
    public void setListButtonsVisible(boolean visible) {
    }

    @Override
    public Box getRoot() {
        return root;
    }

    @Override
    public TexturedButton getUpButton() {
        return dummyUpButton;
    }

    @Override
    public TexturedButton getDownButton() {
        return dummyDownButton;
    }

    @Override
    public TexturedButton getDeleteButton() {
        return dummyDeleteButton;
    }

    @Override
    public TexturedButton getResetButton() {
        return resetPotionButton;
    }

    public TexturedButton getRemoveColorButton() {
        return removeColorButton;
    }

    public TexturedButton getChooseColorButton() {
        return chooseColorButton;
    }

    public TexturedButton getResetColorButton() {
        return resetColorButton;
    }

    public ItemView getPotionView() {
        return potionView;
    }
}
