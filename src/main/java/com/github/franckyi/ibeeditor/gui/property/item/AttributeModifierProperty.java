package com.github.franckyi.ibeeditor.gui.property.item;

import com.github.franckyi.ibeeditor.gui.base.GuiDoubleValueField;
import com.github.franckyi.ibeeditor.gui.base.GuiEnumButton;
import com.github.franckyi.ibeeditor.gui.base.GuiIntValueField;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.models.AttributeModifierModel;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.Arrays;
import java.util.function.Supplier;

public class AttributeModifierProperty extends BaseProperty<AttributeModifierModel> {

    private GuiTextField nameTextField;
    private GuiDoubleValueField amountTextField;
    private GuiIntValueField operationTextField;
    private GuiEnumButton<EntityEquipmentSlot> slotButton;

    public AttributeModifierProperty(Supplier<AttributeModifierModel> value) {
        super("", value);
        nameTextField = new GuiTextField(0, mc.fontRenderer, 0, 0, 100, 14);
        amountTextField = new GuiDoubleValueField(0, mc.fontRenderer, 0, 0, 40, 14);
        operationTextField = new GuiIntValueField(0, mc.fontRenderer, 0, 0, 20, 14, 0, 2);
        slotButton = new GuiEnumButton<>(0, 0, 0, 60, 20, "", Arrays.asList(EntityEquipmentSlot.values()));
        getButtonList().add(slotButton);
        getTextfieldList().addAll(Arrays.asList(nameTextField, amountTextField, operationTextField));
        init();
    }

    @Override
    protected void init() {
        nameTextField.setText(getValue().getName());
        amountTextField.setText(String.valueOf(getValue().getAmount()));
        operationTextField.setText(String.valueOf(getValue().getOperation()));
        slotButton.setValue(getValue().getSlot());
    }

    @Override
    protected void place() {
        super.place();
        nameTextField.x = slotLeft + 10;
        nameTextField.y = slotTop + 3;
        amountTextField.x = slotLeft + 120;
        amountTextField.y = slotTop + 3;
        operationTextField.x = slotLeft + 170;
        operationTextField.y = slotTop + 3;
        slotButton.x = slotLeft + 200;
        slotButton.y = slotTop;
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected);
        nameTextField.drawTextBox();
        amountTextField.drawTextBox();
        operationTextField.drawTextBox();
        slotButton.drawButton(mc, mouseX, mouseY);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        nameTextField.textboxKeyTyped(typedChar, keyCode);
        amountTextField.textboxKeyTyped(typedChar, keyCode);
        operationTextField.textboxKeyTyped(typedChar, keyCode);
        getValue().setName(nameTextField.getText());
        getValue().setAmount(amountTextField.getValue());
        getValue().setOperation(operationTextField.getValue());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        nameTextField.mouseClicked(mouseX, mouseY, mouseButton);
        amountTextField.mouseClicked(mouseX, mouseY, mouseButton);
        operationTextField.mouseClicked(mouseX, mouseY, mouseButton);
        slotButton.mousePressed(mc, mouseX, mouseY);
        getValue().setSlot(slotButton.getValue());
    }

    @Override
    public void updateScreen() {
        nameTextField.updateCursorCounter();
        amountTextField.updateCursorCounter();
        operationTextField.updateCursorCounter();
    }
}