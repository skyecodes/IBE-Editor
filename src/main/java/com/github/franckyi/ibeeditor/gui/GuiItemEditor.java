package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.gui.property.PropertyFactory;
import com.github.franckyi.ibeeditor.util.IBEUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class GuiItemEditor extends GuiEditor {

    private ItemStack stack = IBEUtil.getItem();

    protected GuiItemEditor(GuiScreen parentScreen) {
        super(new PropertyFactory()
                .newCategory("category0").addString("property0")
                .nextCategory("category1").addString("property1", "value1")
                .endCategoryAndCreate(), parentScreen);
    }

    protected GuiItemEditor() {
        this(null);
    }

    @Override
    protected void apply(Set<BaseProperty<?>> properties) {

    }
}
