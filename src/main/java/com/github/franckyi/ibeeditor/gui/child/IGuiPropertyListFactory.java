package com.github.franckyi.ibeeditor.gui.child;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import net.minecraft.client.Minecraft;

import java.util.List;

@FunctionalInterface
public interface IGuiPropertyListFactory<T extends BaseProperty<?>> {

    GuiPropertyList<T> create(GuiEditor parent, Minecraft mc, List<T> properties);

}
