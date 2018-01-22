package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import net.minecraft.client.gui.GuiScreen;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GuiEntityEditor extends GuiEditor {

    protected GuiEntityEditor(GuiScreen parentScreen) {
        super(parentScreen);
    }

    protected GuiEntityEditor() {
        this(null);
    }

}
