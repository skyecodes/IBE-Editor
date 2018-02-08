package com.github.franckyi.ibeeditor.gui.child.item;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.child.GuiPropertyListEditable;
import com.github.franckyi.ibeeditor.gui.property.item.PotionEffectProperty;
import com.github.franckyi.ibeeditor.models.PotionEffectModel;
import net.minecraft.client.Minecraft;

import java.util.List;

public class GuiPropertyListPotionEffects extends GuiPropertyListEditable<PotionEffectProperty> {

    public GuiPropertyListPotionEffects(GuiEditor parent, Minecraft mcIn, List<PotionEffectProperty> properties) {
        super(parent, mcIn, 40, properties);
        init();
    }

    @Override
    protected PotionEffectProperty newProperty(int index) {
        return new PotionEffectProperty(PotionEffectModel::new);
    }
}
