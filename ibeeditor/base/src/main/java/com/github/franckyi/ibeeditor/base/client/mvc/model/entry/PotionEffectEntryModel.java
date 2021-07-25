package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public class PotionEffectEntryModel extends EntryModel {
    private final IntegerProperty idProperty, amplifierProperty, durationProperty;
    private final BooleanProperty ambientProperty, showParticlesProperty, showIconProperty;
    private final PotionEffectConsumer callback;

    public PotionEffectEntryModel(CategoryModel category, int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon, PotionEffectConsumer callback) {
        super(category);
        idProperty = DataBindings.getPropertyFactory().createIntegerProperty(id);
        amplifierProperty = DataBindings.getPropertyFactory().createIntegerProperty(amplifier);
        durationProperty = DataBindings.getPropertyFactory().createIntegerProperty(duration);
        ambientProperty = DataBindings.getPropertyFactory().createBooleanProperty(ambient);
        showParticlesProperty = DataBindings.getPropertyFactory().createBooleanProperty(showParticles);
        showIconProperty = DataBindings.getPropertyFactory().createBooleanProperty(showIcon);
        this.callback = callback;
    }

    @Override
    public void apply() {
        callback.consume(getId(), getAmplifier(), getDuration(), isAmbient(), isShowParticles(), isShowIcon());
    }

    public int getId() {
        return idProperty().getValue();
    }

    public IntegerProperty idProperty() {
        return idProperty;
    }

    public int getAmplifier() {
        return amplifierProperty().getValue();
    }

    public IntegerProperty amplifierProperty() {
        return amplifierProperty;
    }

    public int getDuration() {
        return durationProperty().getValue();
    }

    public IntegerProperty durationProperty() {
        return durationProperty;
    }

    public boolean isAmbient() {
        return ambientProperty().getValue();
    }

    public BooleanProperty ambientProperty() {
        return ambientProperty;
    }

    public boolean isShowParticles() {
        return showParticlesProperty().getValue();
    }

    public BooleanProperty showParticlesProperty() {
        return showParticlesProperty;
    }

    public boolean isShowIcon() {
        return showIconProperty().getValue();
    }

    public BooleanProperty showIconProperty() {
        return showIconProperty;
    }

    @Override
    public Type getType() {
        return Type.POTION_EFFECT;
    }

    @FunctionalInterface
    public interface PotionEffectConsumer {
        void consume(int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon);
    }
}
