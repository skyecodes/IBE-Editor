package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.gui.property.PropertyFactory;
import com.github.franckyi.ibeeditor.util.IBEUtil;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GuiBlockEditor extends GuiEditor {

    protected GuiBlockEditor(GuiScreen parentScreen) {
        super(parentScreen);
    }

    protected GuiBlockEditor() {
        this(null);
    }

}
