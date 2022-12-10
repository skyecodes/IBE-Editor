package com.github.franckyi.ibeeditor.client.screen.model.entry.item;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.ibeeditor.client.ClientCache;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.SelectionEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ListSelectionElementModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class PotionEffectEntryModel extends SelectionEntryModel {
    private final IntegerProperty amplifierProperty, durationProperty;
    private final BooleanProperty ambientProperty, showParticlesProperty, showIconProperty;
    private final PotionEffectConsumer callback;

    public PotionEffectEntryModel(CategoryModel category, int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon, PotionEffectConsumer callback) {
        super(category, null, BuiltInRegistries.MOB_EFFECT.getKey(BuiltInRegistries.MOB_EFFECT.byId(id)).toString(), s -> {
        });
        amplifierProperty = IntegerProperty.create(amplifier);
        durationProperty = IntegerProperty.create(duration);
        ambientProperty = BooleanProperty.create(ambient);
        showParticlesProperty = BooleanProperty.create(showParticles);
        showIconProperty = BooleanProperty.create(showIcon);
        this.callback = callback;
    }

    @Override
    public void apply() {
        callback.consume(BuiltInRegistries.MOB_EFFECT.getId(BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.tryParse(getValue()))), getAmplifier(), getDuration(), isAmbient(), isShowParticles(), isShowIcon());
    }

    public int getAmplifier() {
        return amplifierProperty().getValue();
    }

    public IntegerProperty amplifierProperty() {
        return amplifierProperty;
    }

    public void setAmplifier(int value) {
        amplifierProperty().setValue(value);
    }

    public int getDuration() {
        return durationProperty().getValue();
    }

    public IntegerProperty durationProperty() {
        return durationProperty;
    }

    public void setDuration(int value) {
        durationProperty().setValue(value);
    }

    public boolean isAmbient() {
        return ambientProperty().getValue();
    }

    public BooleanProperty ambientProperty() {
        return ambientProperty;
    }

    public void setAmbient(boolean value) {
        ambientProperty().setValue(value);
    }

    public boolean isShowParticles() {
        return showParticlesProperty().getValue();
    }

    public BooleanProperty showParticlesProperty() {
        return showParticlesProperty;
    }

    public void setShowParticles(boolean value) {
        showParticlesProperty().setValue(value);
    }

    public boolean isShowIcon() {
        return showIconProperty().getValue();
    }

    public BooleanProperty showIconProperty() {
        return showIconProperty;
    }

    public void setShowIcon(boolean value) {
        showIconProperty().setValue(value);
    }

    @Override
    public Type getType() {
        return Type.POTION_EFFECT;
    }

    @Override
    public List<String> getSuggestions() {
        return ClientCache.getEffectSuggestions();
    }

    @Override
    public MutableComponent getSelectionScreenTitle() {
        return ModTexts.EFFECTS;
    }

    @Override
    public List<? extends ListSelectionElementModel> getSelectionItems() {
        return ClientCache.getEffectSelectionItems();
    }

    @FunctionalInterface
    public interface PotionEffectConsumer {
        void consume(int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon);
    }
}
