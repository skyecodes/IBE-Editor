package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.List;

import static com.github.franckyi.guapi.api.GuapiHelper.hBox;
import static com.github.franckyi.guapi.api.GuapiHelper.itemView;

public class ArmorColorSelectionScreenView extends ColorSelectionScreenView {
    private List<ItemView> exampleItems;

    @Override
    protected Node createExample() {
        exampleItems = Arrays.asList(
                itemView(new ItemStack(Items.LEATHER_HELMET)),
                itemView(new ItemStack(Items.LEATHER_CHESTPLATE)),
                itemView(new ItemStack(Items.LEATHER_LEGGINGS)),
                itemView(new ItemStack(Items.LEATHER_BOOTS)),
                itemView(new ItemStack(Items.LEATHER_HORSE_ARMOR))
        );
        return hBox(exampleItems).spacing(5);
    }

    public List<ItemView> getExampleItems() {
        return exampleItems;
    }
}
