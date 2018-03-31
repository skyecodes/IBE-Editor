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

    private GuiEnumButton<EntityEquipmentSlot> slotButton;
    private GuiTextField nameTextField;
    private GuiDoubleValueField amountTextField;
    private GuiIntValueField operationTextField;

    public AttributeModifierProperty(Supplier<AttributeModifierModel> value) {
        super("", value);
        slotButton = new GuiEnumButton<>(0, 0, 0, 60, 20, "", Arrays.asList(EntityEquipmentSlot.values()));
        nameTextField = new GuiTextField(0, mc.fontRenderer, 0, 0, 100, 14);
        amountTextField = new GuiDoubleValueField(0, mc.fontRenderer, 0, 0, 40, 14);
        operationTextField = new GuiIntValueField(0, mc.fontRenderer, 0, 0, 20, 14);
        getButtonList().add(slotButton);
        getTextfieldList().addAll(Arrays.asList(nameTextField, amountTextField, operationTextField));
        init();
    }

    @Override
    protected void init() {
        nameTextField.setText(getValue().getName());
        amountTextField.setText(String.valueOf(getValue().getAmount()));
        operationTextField.setText(String.valueOf(getValue().getOperation()));
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
    public void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        getValue().setName(nameTextField.getText());
        getValue().setAmount(amountTextField.getValue());
        getValue().setOperation(operationTextField.getValue());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        getValue().setSlot(slotButton.getValue());
    }

}
