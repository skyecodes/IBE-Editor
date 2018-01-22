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

    protected Map<String, List<BaseProperty<?>>> propertiesMap;

    private GuiScreen parentScreen;

    private GuiCategoryList categories;
    private GuiPropertyList properties;

    private GuiButton cancel, apply, done;

    protected GuiEditor(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    protected GuiEditor() {
        this(null);
    }

    protected void setPropertiesMap(Map<String,List<BaseProperty<?>>> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button == cancel) {
            mc.displayGuiScreen(parentScreen);
        } else {
            apply();

            if(button == done) {
                mc.displayGuiScreen(parentScreen);
            }
        }
    }

    protected void apply() {
        propertiesMap.values().stream()
                .flatMap(Collection::stream)
                .forEach(BaseProperty::apply);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground(0);
        properties.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
        categories.drawScreen(mouseX, mouseY, partialTicks);
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
        categories.registerScrollButtons(7, 8);
        properties = new GuiPropertyList(mc, 2 * width / 3 - 20, height - 60, 20, height - 40);
        properties.setSlotXBoundsFromLeft(width / 3 + 10);
        properties.registerScrollButtons(7, 8);
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

    @Override
    public void updateScreen() {
        properties.updateScreen();
    }

    public void selectCategory(String categoryName) {
        properties.setProperties(propertiesMap.get(categoryName));
    }
}
