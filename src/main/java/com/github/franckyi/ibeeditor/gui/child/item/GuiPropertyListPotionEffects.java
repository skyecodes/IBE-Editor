package com.github.franckyi.ibeeditor.gui.child.item;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.child.GuiPropertyListEditable;
import com.github.franckyi.ibeeditor.gui.property.item.PotionEffectProperty;
import com.github.franckyi.ibeeditor.models.PotionEffectModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

import java.util.List;

public class GuiPropertyListPotionEffects extends GuiPropertyListEditable<PotionEffectProperty> {

    public GuiPropertyListPotionEffects(GuiEditor parent, Minecraft mcIn, List<PotionEffectProperty> properties) {
        super(parent, mcIn, 40, properties);
        setHasListHeader(true, 14);
        init();
    }

    @Override
    protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn) {
        mc.fontRenderer.drawString("ID", right - 160, insideTop + 2, 0xffffff);
        mc.fontRenderer.drawString("Amplifier", right - 130, insideTop + 2, 0xffffff);
        mc.fontRenderer.drawString("Duration", right - 80, insideTop + 2, 0xffffff);
    }

    @Override
    protected PotionEffectProperty newProperty(int index) {
        return new PotionEffectProperty(PotionEffectModel::new);
    }
}
