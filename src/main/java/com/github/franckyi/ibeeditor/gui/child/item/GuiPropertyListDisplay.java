package com.github.franckyi.ibeeditor.gui.child.item;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.child.GuiPropertyListEditable;
import com.github.franckyi.ibeeditor.gui.property.base.FormattedStringProperty;
import net.minecraft.client.Minecraft;

import java.util.List;

public class GuiPropertyListDisplay extends GuiPropertyListEditable<FormattedStringProperty> {

    public GuiPropertyListDisplay(GuiEditor parent, Minecraft mcIn, List<FormattedStringProperty> properties) {
        super(parent, mcIn, 25, properties);
        init();
    }

    @Override
    protected int getListStart() {
        return 1;
    }

    @Override
    protected void listUpdated() {
        super.listUpdated();
        for (int i = 1; i < properties.size(); ++i) {
            properties.get(i).setName(String.format("Lore %d", i));
        }
    }

    @Override
    protected FormattedStringProperty newProperty(int index) {
        return new FormattedStringProperty(String.format("Lore %d", index + 1), () -> "", s -> {
        });
    }
}
