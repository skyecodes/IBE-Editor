package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;

import java.util.Arrays;
import java.util.List;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ArmorColorSelectionScreenView extends ColorSelectionScreenView {
    private List<ItemView> exampleItems;

    @Override
    protected Node createExample() {
        exampleItems = Arrays.asList(
                itemView(Game.getCommon().createItem("minecraft:leather_helmet")),
                itemView(Game.getCommon().createItem("minecraft:leather_chestplate")),
                itemView(Game.getCommon().createItem("minecraft:leather_leggings")),
                itemView(Game.getCommon().createItem("minecraft:leather_boots")),
                itemView(Game.getCommon().createItem("minecraft:leather_horse_armor"))
        );
        return hBox(exampleItems).spacing(5);
    }

    public List<ItemView> getExampleItems() {
        return exampleItems;
    }
}
