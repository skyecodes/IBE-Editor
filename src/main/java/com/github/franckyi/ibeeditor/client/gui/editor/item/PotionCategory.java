package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.ColorProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.ItemPotionEffectProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.model.PotionEffectModel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PotionCategory extends EditableCategory<PotionEffectModel> {

    private final ItemStack itemStack;
    private final List<EffectInstance> effects;

    public PotionCategory(ItemStack itemStack) {
        super(1);
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
        if (effect != null && !effect.isDisabled()) {
            effects.add(effect);
        }
    }

    @Override
    protected AbstractProperty<PotionEffectModel> createNewProperty(PotionEffectModel initialValue, int index) {
        return new ItemPotionEffectProperty(this, index, initialValue, this::addEffect);
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
            TexturedButton previewButton = new TexturedButton(stack, false);
            previewButton.getTooltipText().add(ITextComponent.getTextComponentOrEmpty("Preview"));
            previewGroup.getChildren().set(0, previewButton);
        }

        @Override
        protected void build() {
            super.build();
            this.getNode().getChildren().add(5, refreshButton = new TexturedButton("download.png",
                    TextFormatting.AQUA + "Calculate color from effects"));
            refreshButton.getOnMouseClickedListeners().add(e -> {
                List<AbstractProperty<?>> children = PotionCategory.this.getChildren();
                List<EffectInstance> effects = children.subList(1, children.size() - 1).stream()
                        .map(abstractProperty -> ((EffectInstance) ((ItemPotionEffectProperty) abstractProperty).getValue()))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                effects.addAll(PotionUtils.getPotionFromItem(itemStack).getEffects());
                this.setValue(new Color(PotionUtils.getPotionColorFromEffectList(effects)));
            });
            TexturedButton previewButton = new TexturedButton(new ItemStack(Items.POTION), false);
            previewButton.getTooltipText().add(ITextComponent.getTextComponentOrEmpty("Preview"));
            previewGroup.getChildren().add(previewButton);
        }

        @Override
        public void updateSize(int listWidth) {
            previewGroup.setPrefWidth(listWidth - OFFSET - 240);
        }
    }
}
