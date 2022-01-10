package com.github.franckyi.ibeeditor.client.screen.view.entry.item;

import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.ModTextures;
import com.github.franckyi.ibeeditor.client.screen.view.entry.LabeledEntryView;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.List;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class ArmorColorEntryView extends LabeledEntryView {
    private TexturedButton chooseColorButton;
    private TexturedButton removeColorButton;
    private List<ItemView> itemViews;

    @Override
    protected Node createLabeledContent() {
        itemViews = Arrays.asList(
                itemView(new ItemStack(Items.LEATHER_HELMET)),
                itemView(new ItemStack(Items.LEATHER_CHESTPLATE)),
                itemView(new ItemStack(Items.LEATHER_LEGGINGS)),
                itemView(new ItemStack(Items.LEATHER_BOOTS)),
                itemView(new ItemStack(Items.LEATHER_HORSE_ARMOR))
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
