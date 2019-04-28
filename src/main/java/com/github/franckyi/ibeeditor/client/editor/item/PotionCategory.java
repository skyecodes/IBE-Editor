package com.github.franckyi.ibeeditor.client.editor.item;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.guapi.node.IntegerField;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.client.editor.common.AbstractProperty;
import com.github.franckyi.ibeeditor.client.editor.common.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.editor.common.property.IEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyString;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class PotionCategory extends EditableCategory<PotionCategory.PotionEffectModel> {

    private final ItemStack itemStack;
    private final List<PotionEffect> effects;
    private PotionType potionType;
    private List<PotionEffect> baseEffects;

    public PotionCategory(ItemStack itemStack) {
        super(1);
        mc.mouseHelper.ungrabMouse();
        this.itemStack = itemStack;
        potionType = PotionUtils.getPotionFromItem(itemStack);
        effects = PotionUtils.getFullEffectsFromItem(itemStack);
        this.addAll(
                new PropertyString("Base potion", potionType.getRegistryName().toString(), this::setPotion, 65),
                new AddButton("Add potion effect")
        );
        this.updateBaseEffects();
        effects.forEach(potionEffect -> this.addProperty(new PotionEffectModel(potionEffect, false)));
    }

    private void addEffect(PotionEffectModel effect) {
        if (!effect.isDisabled()) {
            effects.add(effect);
        }
    }

    private void setPotion(String potion) {
        itemStack.getOrCreateTag().putString("Potion", potion);
        potionType = PotionType.getPotionTypeForName(potion);
        if (!baseEffects.isEmpty()) {
            this.getChildren().subList(1, baseEffects.size() + 1).clear();
        }
        this.updateBaseEffects();
    }

    private void updateBaseEffects() {
        baseEffects = potionType.getEffects();
        baseEffects.forEach(potionEffect -> this.addProperty(new PotionEffectModel(potionEffect, true)));
    }

    @Override
    protected AbstractProperty<PotionEffectModel> createNewProperty(PotionEffectModel initialValue, int index) {
        return new PotionEffectProperty(index, initialValue, this::addEffect);
    }

    @Override
    protected PotionEffectModel getDefaultPropertyValue() {
        return new PotionEffectModel(MobEffects.SPEED);
    }

    @Override
    public void apply() {
        effects.clear();
        itemStack.getOrCreateTag().remove("CustomPotionEffects");
        super.apply();
        PotionUtils.appendEffects(itemStack, effects);
    }

    private class PotionEffectProperty extends AbstractProperty<PotionEffectModel> implements IEditableCategoryProperty {

        private final PropertyControls controls;

        private TextField name;
        private IntegerField amplifier;
        private IntegerField duration;
        private CheckBox ambient;
        private CheckBox showParticles;
        private CheckBox showIcon;

        public PotionEffectProperty(int index, PotionEffectModel initialValue, Consumer<PotionEffectModel> action) {
            super(initialValue, action);
            controls = new PropertyControls(PotionCategory.this, index);
            IEditableCategoryProperty.super.build();
            if (initialValue.isDisabled()) {
                resetButton.setDisabled(true);
                controls.getRemove().setDisabled(true);
            }
        }

        @Override
        protected PotionEffectModel getValue() {
            return new PotionEffectModel(ForgeRegistries.POTIONS.getValue(ResourceLocation.tryCreate(name.getValue())),
                    duration.getValue(), amplifier.getValue(), ambient.getValue(),
                    showParticles.getValue(), showIcon.getValue(), initialValue.isDisabled());
        }

        @Override
        protected void setValue(PotionEffectModel value) {
            name.setValue(value.getPotion().getRegistryName().toString());
            amplifier.setValue(value.getAmplifier());
            duration.setValue(value.getDuration());
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
        public PropertyControls getControls() {
            return controls;
        }

        @Override
        public void build() {
            this.getNode().setAlignment(Pos.LEFT);
            this.addAll(
                    name = new TextField(),
                    amplifier = new IntegerField(),
                    duration = new IntegerField(),
                    ambient = new CheckBox("A"),
                    showParticles = new CheckBox("P"),
                    showIcon = new CheckBox("I")
            );
            amplifier.setPrefWidth(20);
            duration.setPrefWidth(30);
            name.getTooltipText().add("Potion name");
            amplifier.getTooltipText().add("Amplifier (0 => level 1)");
            duration.getTooltipText().add("Duration (in ticks)");
            ambient.getTooltipText().add("Ambient");
            showParticles.getTooltipText().add("Show particles");
            showIcon.getTooltipText().add("Show icon");
        }

        @Override
        public void updateSize(int listWidth) {
            name.setPrefWidth(listWidth - 191);
        }
    }

    public class PotionEffectModel extends PotionEffect {
        private final boolean disabled;

        public PotionEffectModel(Potion potion, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, boolean disabled) {
            super(potion, duration, amplifier, ambient, showParticles, showIcon);
            this.disabled = disabled;
        }

        public PotionEffectModel(PotionEffect effect, boolean disabled) {
            super(effect);
            this.disabled = disabled;
        }

        public PotionEffectModel(Potion potion) {
            super(potion);
            disabled = false;
        }

        public boolean isDisabled() {
            return disabled;
        }
    }
}
