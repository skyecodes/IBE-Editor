package com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom;

import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.model.PotionEffectModel;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class ItemPotionEffectProperty extends BasePotionEffectProperty<PotionEffectModel> {

    private CheckBox ambient;
    private CheckBox showParticles;
    private CheckBox showIcon;

    public ItemPotionEffectProperty(EditableCategory<?> category, int index, PotionEffectModel initialValue, Consumer<PotionEffectModel> action) {
        super(category, index, initialValue, action);
        if (initialValue.isDisabled()) {
            resetButton.setDisabled(true);
            controls.getRemove().setDisabled(true);
        }
    }

    @Override
    public PotionEffectModel getValue() {
        Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryCreate(name.getValue()));
        return effect == null ? null : new PotionEffectModel(effect,
                duration.getValue(), amplifier.getValue(), ambient.getValue(),
                showParticles.getValue(), showIcon.getValue(), initialValue.isDisabled());
    }

    @Override
    protected void setValue(PotionEffectModel value) {
        super.setValue(value);
        ambient.setValue(value.isAmbient());
        showParticles.setValue(value.doesShowParticles());
        showIcon.setValue(value.isShowIcon());
        if (value.isDisabled()) {
            name.getView().setEnabled(false);
            amplifier.getView().setEnabled(false);
            duration.getView().setEnabled(false);
            ambient.setDisabled(true);
            showParticles.setDisabled(true);
            showIcon.setDisabled(true);
        }
    }

    @Override
    public void build() {
        super.build();
        this.addAll(
                ambient = new CheckBox("A"),
                showParticles = new CheckBox("P"),
                showIcon = new CheckBox("I")
        );
        ambient.getTooltipText().add("Ambient");
        showParticles.getTooltipText().add("Show particles");
        showIcon.getTooltipText().add("Show icon");
    }

    @Override
    public void updateSize(int listWidth) {
        name.setPrefWidth(listWidth - OFFSET - 201);
    }
}
