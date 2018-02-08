package com.github.franckyi.ibeeditor.gui.child.item;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.child.GuiPropertyListEditable;
import com.github.franckyi.ibeeditor.gui.property.StringProperty;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

import java.util.List;

public class GuiPropertyListBlock extends GuiPropertyListEditable<StringProperty> {

    public GuiPropertyListBlock(GuiEditor parent, Minecraft mcIn, List<StringProperty> properties) {
        super(parent, mcIn, 25, properties);
        setHasListHeader(true, 14);
        init();
    }

    @Override
    protected void listUpdated() {
        super.listUpdated();
        updateNames();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        updateNames();
    }

    private void updateNames() {
        properties.forEach(property -> {
            Block b = Block.getBlockFromName(property.getValue());
            property.setName(b != null ? b.getLocalizedName() : "Unknown block");
        });
    }

    @Override
    protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn) {
        mc.fontRenderer.drawString("Block ID (example : minecraft:stone)", insideLeft + 5, insideTop + 2, 0xffffff);
    }

    @Override
    protected StringProperty newProperty(int index) {
        return new StringProperty("", String::new, s -> {
        });
    }
}
