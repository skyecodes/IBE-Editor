package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.EntityPotionEffectProperty;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityPotionEffectsCategory extends EditableCategory<EffectInstance> {

    private final LivingEntity entity;
    private final Set<Effect> baseEffects;
    private final Set<EffectInstance> effects;
    private boolean showParticles;

    public EntityPotionEffectsCategory(LivingEntity entity) {
        super(1);
        this.entity = entity;
        effects = new HashSet<>();
        baseEffects = entity.getActivePotionEffects().stream().map(EffectInstance::getPotion).collect(Collectors.toSet());
        showParticles = entity.getActivePotionEffects().stream().allMatch(EffectInstance::isAmbient);
        this.addAll(
                new PropertyBoolean("Show particles", showParticles, this::setShowParticles),
                new AddButton("Add potion effect")
        );
        entity.getActivePotionEffects().forEach(this::addProperty);
    }

    private void setShowParticles(boolean showParticles) {
        this.showParticles = showParticles;
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
    public void postApply() {
        baseEffects.forEach(entity::removeActivePotionEffect);
        effects.stream()
                .map(effect -> new EffectInstance(effect.getPotion(), effect.getDuration(),
                        effect.getAmplifier(), showParticles, false, false))
                .forEach(entity::addPotionEffect);
    }
}
