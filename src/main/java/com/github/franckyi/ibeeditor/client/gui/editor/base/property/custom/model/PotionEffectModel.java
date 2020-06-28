package com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.model;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class PotionEffectModel extends EffectInstance {
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
