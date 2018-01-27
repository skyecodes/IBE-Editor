package com.github.franckyi.ibeeditor.gui.child;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.gui.property.StringProperty;
import net.minecraft.client.Minecraft;

import java.util.List;

public class GuiPropertyListItemDisplay extends GuiPropertyListEditable {

    public GuiPropertyListItemDisplay(GuiEditor parent, Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, List<BaseProperty<?>> properties) {
        super(parent, mcIn, widthIn, heightIn, topIn, bottomIn, properties);
        listUpdated();
    }

    @Override
    protected int getListStart() {
        return 1;
    }

    @Override
    protected void listUpdated() {
        for(int i = 1; i < properties.size(); ++i) {
            properties.get(i).setName(String.format("Lore %d", i));
        }
    }

    @Override
    protected BaseProperty<?> newProperty(int index) {
        return new StringProperty(String.format("Lore %d", index + 1), () -> "", s -> {});
    }
}
