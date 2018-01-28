package com.github.franckyi.ibeeditor.gui.child;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.property.AttributeModifierProperty;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.util.AttributeModifierModel;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.List;

public class GuiPropertyListAttributeModifier extends GuiPropertyListEditable {

    public GuiPropertyListAttributeModifier(GuiEditor parent, Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, List<BaseProperty<?>> properties) {
        super(parent, mcIn, widthIn, heightIn, topIn, bottomIn, properties);
        if (properties.isEmpty()) properties.add(newProperty(0));
    }

    @Override
    protected void remove(int index) {
        super.remove(index);
        if (properties.isEmpty()) properties.add(newProperty(0));
    }

    @Override
    protected BaseProperty<?> newProperty(int index) {
        return new AttributeModifierProperty(() -> new AttributeModifierModel("", 0, 0, EntityEquipmentSlot.MAINHAND));
    }
}