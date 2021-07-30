package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;

import java.util.Arrays;
import java.util.List;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ArmorColorEntryView extends LabeledEntryView {
    private TexturedButton chooseColorButton;
    private TexturedButton removeColorButton;
    private List<ItemView> itemViews;

    @Override
    protected Node createLabeledContent() {
        itemViews = Arrays.asList(
                itemView(Game.getCommon().createItem("minecraft:leather_helmet")),
                itemView(Game.getCommon().createItem("minecraft:leather_chestplate")),
                itemView(Game.getCommon().createItem("minecraft:leather_leggings")),
                itemView(Game.getCommon().createItem("minecraft:leather_boots")),
                itemView(Game.getCommon().createItem("minecraft:leather_horse_armor"))
        );
        return hBox(root -> {
            root.add(chooseColorButton = texturedButton("ibeeditor:textures/gui/color_custom.png", 16, 16, false)
                    .tooltip(translated("ibeeditor.gui.choose_custom_color")));
            root.add(removeColorButton = texturedButton("ibeeditor:textures/gui/remove.png", 16, 16, false)
                    .tooltip(translated("ibeeditor.gui.remove_custom_color").red()));
            root.getChildren().addAll(itemViews);
            root.spacing(5);
        });
    }

    public TexturedButton getChooseColorButton() {
        return chooseColorButton;
    }

    public TexturedButton getRemoveColorButton() {
        return removeColorButton;
    }

    public List<ItemView> getItemViews() {
        return itemViews;
    }
}
