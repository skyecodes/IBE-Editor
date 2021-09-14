package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;

import java.util.Arrays;
import java.util.List;

import static com.github.franckyi.guapi.api.GuapiHelper.hBox;
import static com.github.franckyi.guapi.api.GuapiHelper.itemView;

public class ArmorColorSelectionScreenView extends ColorSelectionScreenView {
    private static final IIdentifier LEATHER_HELMET = IIdentifier.of("minecraft:leather_helmet");
    private static final IIdentifier LEATHER_CHESTPLATE = IIdentifier.of("minecraft:leather_chestplate");
    private static final IIdentifier LEATHER_LEGGINGS = IIdentifier.of("minecraft:leather_leggings");
    private static final IIdentifier LEATHER_BOOTS = IIdentifier.of("minecraft:leather_boots");
    private static final IIdentifier LEATHER_HORSE_ARMOR = IIdentifier.of("minecraft:leather_horse_armor");
    private List<ItemView> exampleItems;

    @Override
    protected Node createExample() {
        exampleItems = Arrays.asList(
                itemView(Game.getCommon().createItemFromId(LEATHER_HELMET)),
                itemView(Game.getCommon().createItemFromId(LEATHER_CHESTPLATE)),
                itemView(Game.getCommon().createItemFromId(LEATHER_LEGGINGS)),
                itemView(Game.getCommon().createItemFromId(LEATHER_BOOTS)),
                itemView(Game.getCommon().createItemFromId(LEATHER_HORSE_ARMOR))
        );
        return hBox(exampleItems).spacing(5);
    }

    public List<ItemView> getExampleItems() {
        return exampleItems;
    }
}
