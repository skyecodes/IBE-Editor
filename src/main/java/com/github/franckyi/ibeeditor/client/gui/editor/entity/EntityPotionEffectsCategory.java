package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.EntityPotionEffectProperty;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.HashSet;
import java.util.Set;

public class EntityPotionEffectsCategory extends EditableCategory<EffectInstance> {

    private final LivingEntity entity;
    private final Set<EffectInstance> effects;
    private boolean hideParticles;

    public EntityPotionEffectsCategory(LivingEntity entity) {
        super(1);
        this.entity = entity;
        effects = new HashSet<>();
        hideParticles = entity.getActivePotionEffects().stream().allMatch(EffectInstance::isAmbient);
        this.addAll(
                new PropertyBoolean("Hide particles", hideParticles, this::setHideParticles),
                new AddButton("Add potion effect")
        );
        entity.getActivePotionEffects().forEach(this::addProperty);
    }

    private void setHideParticles(boolean hideParticles) {
        this.hideParticles = hideParticles;
    }

    @Override
    protected AbstractProperty<EffectInstance> createNewProperty(EffectInstance initialValue, int index) {
        return new EntityPotionEffectProperty(this, index, initialValue, effects::add);
    }

    @Override
    protected EffectInstance getDefaultPropertyValue() {
        return new EffectInstance(Effects.SPEED);
    }


    @Override
    public void apply() {
        effects.clear();
        super.apply();
        entity.getActivePotionMap().clear();
        effects.stream()
                .map(effect -> new EffectInstance(effect.getPotion(), effect.getDuration(),
                        effect.getAmplifier(), hideParticles, false, false))
                .forEach(effectInstance -> entity.getActivePotionMap().put(effectInstance.getPotion(), effectInstance));
    }
}
