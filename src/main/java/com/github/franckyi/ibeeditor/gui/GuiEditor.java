package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class GuiEditor extends GuiScreen {

    private Map<String, List<BaseProperty<?>>> propertiesMap;

    protected GuiScreen parentScreen;

    protected GuiCategoryList categories;
    protected GuiPropertyList properties;

    protected GuiButton cancel, apply, done;

    protected GuiEditor(Map<String, List<BaseProperty<?>>> propertiesMap, GuiScreen parentScreen) {
        this.propertiesMap = propertiesMap;
        this.parentScreen = parentScreen;
    }

    protected GuiEditor(Map<String, List<BaseProperty<?>>> categories) {
        this(categories, null);
    }

    protected abstract void apply(Set<BaseProperty<?>> properties);

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground(0);
        super.drawScreen(mouseX, mouseY, partialTicks);
        categories.drawScreen(mouseX, mouseY, partialTicks);
        properties.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        categories.handleMouseInput();
        properties.handleMouseInput();
    }

    @Override
    public void initGui() {
        buttonList.add(cancel = new GuiButton(0, width / 2 - 85, height - 30, 50, 20, "§4Cancel"));
        buttonList.add(apply = new GuiButton(1, width / 2 - 25, height - 30, 50, 20, "§2Apply"));
        buttonList.add(done = new GuiButton(2, width / 2 + 35, height - 30, 50, 20, "§2Done"));
        categories = new GuiCategoryList(this, mc, width / 3 - 20, height - 60, 20, height - 40, propertiesMap);
        categories.setSlotXBoundsFromLeft(10);
        properties = new GuiPropertyList(mc, 2 * width / 3 - 20, height - 60, 20, height - 40);
        properties.setSlotXBoundsFromLeft(width / 3 + 10);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        properties.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        categories.mouseClicked(mouseX, mouseY, mouseButton);
        properties.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void selectCategory(String categoryName) {
        properties.setProperties(propertiesMap.get(categoryName));
    }
}
