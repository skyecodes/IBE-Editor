package com.github.franckyi.ibeeditor.gui.property.item;

import com.github.franckyi.ibeeditor.gui.base.GuiIntValueField;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.models.PotionEffectModel;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.client.config.GuiCheckBox;

import java.util.function.Supplier;

public class PotionEffectProperty extends BaseProperty<PotionEffectModel> {

    private GuiIntValueField idTextField;
    private GuiIntValueField amplifierTextField;
    private GuiIntValueField durationTextField;
    private GuiCheckBox ambientCheckbox;
    private GuiCheckBox showParticlesCheckbox;

    public PotionEffectProperty(Supplier<PotionEffectModel> value) {
        super("Unknown Potion", value);
        idTextField = new GuiIntValueField(0, mc.fontRenderer, 0, 0, 20, 14);
        amplifierTextField = new GuiIntValueField(0, mc.fontRenderer, 0, 0, 20, 14);
        durationTextField = new GuiIntValueField(0, mc.fontRenderer, 0, 0, 60, 14);
        ambientCheckbox = new GuiCheckBox(0, 0, 0, "Ambient", false);
        showParticlesCheckbox = new GuiCheckBox(0, 0, 0, "Show Particles", false);
        init();
    }

    @Override
    protected void init() {
        idTextField.setText(String.valueOf(getValue().getId()));
        amplifierTextField.setText(String.valueOf(getValue().getAmplifier()));
        durationTextField.setText(String.valueOf(getValue().getDuration()));
        ambientCheckbox.setIsChecked(getValue().isAmbient());
        showParticlesCheckbox.setIsChecked(getValue().isShowParticles());
    }

    @Override
    protected void place() {
        super.place();
        idTextField.x = slotRight - 160;
        idTextField.y = slotTop + 3;
        amplifierTextField.x = slotRight - 130;
        amplifierTextField.y = slotTop + 3;
        durationTextField.x = slotRight - 100;
        durationTextField.y = slotTop + 3;
        ambientCheckbox.x = slotLeft + 10;
        ambientCheckbox.y = slotTop + 25;
        showParticlesCheckbox.x = slotLeft + 150;
        showParticlesCheckbox.y = slotTop + 25;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        idTextField.textboxKeyTyped(typedChar, keyCode);
        amplifierTextField.textboxKeyTyped(typedChar, keyCode);
        durationTextField.textboxKeyTyped(typedChar, keyCode);
        getValue().setId(idTextField.getValue());
        getValue().setAmplifier(amplifierTextField.getValue());
        getValue().setDuration(durationTextField.getValue());
        Potion potion = Potion.getPotionById(getValue().getId());
        setName(potion == null ? "Unknown Potion" : potion.getName());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        idTextField.mouseClicked(mouseX, mouseY, mouseButton);
        amplifierTextField.mouseClicked(mouseX, mouseY, mouseButton);
        durationTextField.mouseClicked(mouseX, mouseY, mouseButton);
        ambientCheckbox.mousePressed(mc, mouseX, mouseY);
        showParticlesCheckbox.mousePressed(mc, mouseX, mouseY);
        getValue().setAmbient(ambientCheckbox.isChecked());
        getValue().setShowParticles(showParticlesCheckbox.isChecked());
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected);
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
        idTextField.drawTextBox();
        amplifierTextField.drawTextBox();
        durationTextField.drawTextBox();
        ambientCheckbox.drawButton(mc, mouseX, mouseY);
        showParticlesCheckbox.drawButton(mc, mouseX, mouseY);
    }
}
