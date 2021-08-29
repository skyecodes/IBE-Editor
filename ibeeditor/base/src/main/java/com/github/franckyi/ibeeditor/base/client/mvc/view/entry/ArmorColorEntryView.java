package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.Arrays;
import java.util.List;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ArmorColorEntryView extends LabeledEntryView {
    private static final IIdentifier LEATHER_HELMET = IIdentifier.of("minecraft:leather_helmet");
    private static final IIdentifier LEATHER_CHESTPLATE = IIdentifier.of("minecraft:leather_chestplate");
    private static final IIdentifier LEATHER_LEGGINGS = IIdentifier.of("minecraft:leather_leggings");
    private static final IIdentifier LEATHER_BOOTS = IIdentifier.of("minecraft:leather_boots");
    private static final IIdentifier LEATHER_HORSE_ARMOR = IIdentifier.of("minecraft:leather_horse_armor");
    private TexturedButton chooseColorButton;
    private TexturedButton removeColorButton;
    private List<ItemView> itemViews;

    @Override
    protected Node createLabeledContent() {
        itemViews = Arrays.asList(
                itemView(Game.getCommon().createItemFromId(LEATHER_HELMET)),
                itemView(Game.getCommon().createItemFromId(LEATHER_CHESTPLATE)),
                itemView(Game.getCommon().createItemFromId(LEATHER_LEGGINGS)),
                itemView(Game.getCommon().createItemFromId(LEATHER_BOOTS)),
                itemView(Game.getCommon().createItemFromId(LEATHER_HORSE_ARMOR))
        );
        return hBox(root -> {
            root.add(chooseColorButton = texturedButton(ModTextures.COLOR_CUSTOM, 16, 16, false)
                    .tooltip(ModTexts.choose(ModTexts.CUSTOM_COLOR)));
            root.add(removeColorButton = texturedButton(ModTextures.REMOVE, 16, 16, false)
                    .tooltip(ModTexts.REMOVE_CUSTOM_COLOR));
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
