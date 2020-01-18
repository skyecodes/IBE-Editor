package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.guapi.node.IntegerField;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.IEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.ColorProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PotionCategory extends EditableCategory<PotionCategory.PotionEffectModel> {

    private final ItemStack itemStack;
    private final List<EffectInstance> effects;

    public PotionCategory(ItemStack itemStack) {
        super(1);
        mc.mouseHelper.ungrabMouse();
        this.itemStack = itemStack;
        effects = PotionUtils.getFullEffectsFromItem(itemStack);
        this.addAll(
                new PotionColorProperty(new Color(PotionUtils.getColor(itemStack)), this::setColor),
                new AddButton("Add potion effect")
        );
        effects.forEach(potionEffect -> this.addProperty(new PotionEffectModel(potionEffect, false)));
    }

    private void setColor(Color color) {
        int c = color.getRGB();
        if (c != PotionUtils.getPotionColorFromEffectList(PotionUtils.getEffectsFromStack(itemStack))) {
            itemStack.getOrCreateTag().putInt("CustomPotionColor", c);
        }
    }

    private void addEffect(PotionEffectModel effect) {
        if (!effect.isDisabled()) {
            effects.add(effect);
        }
    }

    @Override
    protected AbstractProperty<PotionEffectModel> createNewProperty(PotionEffectModel initialValue, int index) {
        return new PotionEffectProperty(index, initialValue, this::addEffect);
    }

    @Override
    protected PotionEffectModel getDefaultPropertyValue() {
        return new PotionEffectModel(Effects.SPEED);
    }

    @Override
    public void apply() {
        effects.clear();
        itemStack.getOrCreateTag().remove("CustomPotionEffects");
        itemStack.getOrCreateTag().remove("CustomPotionColor");
        this.getChildren().subList(1, this.getChildren().size()).forEach(AbstractProperty::apply);
        this.getChildren().get(0).apply();
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
            duration.setPrefWidth(32);
            name.getTooltipText().add("Potion name");
            amplifier.getTooltipText().add("Amplifier (0 => level 1)");
            duration.getTooltipText().add("Duration (in ticks)");
            ambient.getTooltipText().add("Ambient");
            showParticles.getTooltipText().add("Show particles");
            showIcon.getTooltipText().add("Show icon");
        }

        @Override
        public void updateSize(int listWidth) {
            name.setPrefWidth(listWidth - 193);
        }
    }

    public static class PotionEffectModel extends EffectInstance {
        private final boolean disabled;

        public PotionEffectModel(Effect effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, boolean disabled) {
            super(effect, duration, amplifier, ambient, showParticles, showIcon);
            this.disabled = disabled;
        }

        public PotionEffectModel(EffectInstance effect, boolean disabled) {
            super(effect);
            this.disabled = disabled;
        }

        public PotionEffectModel(Effect effect) {
            super(effect);
            disabled = false;
        }

        public boolean isDisabled() {
            return disabled;
        }
    }

    private class PotionColorProperty extends ColorProperty {

        private TexturedButton refreshButton;

        public PotionColorProperty(Color initialValue, Consumer<Color> action) {
            super("Potion color", initialValue, action);
        }

        @Override
        protected void setValue(Color value) {
            super.setValue(value);
            ItemStack stack = new ItemStack(Items.POTION);
            stack.getOrCreateTag().putInt("CustomPotionColor", value.getRGB());
            testGroup.getChildren().set(0, new TexturedButton(stack, false));
        }

        @Override
        protected void build() {
            super.build();
            this.getNode().getChildren().add(5, refreshButton = new TexturedButton("download.png",
                    TextFormatting.AQUA + "Calculate color from effects"));
            refreshButton.getOnMouseClickedListeners().add(e -> {
                List<AbstractProperty<?>> children = PotionCategory.this.getChildren();
                List<EffectInstance> effects = children.subList(1, children.size() - 1).stream()
                        .map(abstractProperty -> ((EffectInstance) ((PotionEffectProperty) abstractProperty).getValue()))
                        .collect(Collectors.toList());
                effects.addAll(PotionUtils.getPotionFromItem(itemStack).getEffects());
                this.setValue(new Color(PotionUtils.getPotionColorFromEffectList(effects)));
            });
            testGroup.getChildren().add(new TexturedButton(new ItemStack(Items.POTION), false));
        }

        @Override
        public void updateSize(int listWidth) {
            testGroup.setPrefWidth(listWidth - 239);
        }
    }
}
