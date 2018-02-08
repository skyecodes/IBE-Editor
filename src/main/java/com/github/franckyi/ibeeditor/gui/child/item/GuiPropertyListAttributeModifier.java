package com.github.franckyi.ibeeditor.gui.child.item;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.child.GuiPropertyListEditable;
import com.github.franckyi.ibeeditor.gui.property.item.AttributeModifierProperty;
import com.github.franckyi.ibeeditor.models.AttributeModifierModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.List;

public class GuiPropertyListAttributeModifier extends GuiPropertyListEditable<AttributeModifierProperty> {

    public GuiPropertyListAttributeModifier(GuiEditor parent, Minecraft mcIn, List<AttributeModifierProperty> properties) {
        super(parent, mcIn, 25, properties);
        setHasListHeader(true, 14);
        init();
    }

    @Override
    protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn) {
        mc.fontRenderer.drawString("Name", insideLeft + 10, insideTop + 2, 0xffffff);
        mc.fontRenderer.drawString("Amount", insideLeft + 120, insideTop + 2, 0xffffff);
        mc.fontRenderer.drawString("Operation", insideLeft + 170, insideTop + 2, 0xffffff);
        mc.fontRenderer.drawString("Slot", insideLeft + 235, insideTop + 2, 0xffffff);
    }

    @Override
    protected AttributeModifierProperty newProperty(int index) {
        return new AttributeModifierProperty(() -> new AttributeModifierModel("", 0, 0, EntityEquipmentSlot.MAINHAND));
    }
}
