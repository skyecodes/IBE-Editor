package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.PotionEffectEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.PotionSelectionEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffects;

public class ItemPotionEffectsCategoryModel extends ItemEditorCategoryModel {
    private ListTag potionEffectList;

    public ItemPotionEffectsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.POTION_EFFECTS, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new PotionSelectionEntryModel(this, ModTexts.DEFAULT_POTION,
                getTag().getString("Potion"), getCustomPotionColor(),
                p -> getTag().putString("Potion", p), this::setCustomPotionColor));
        getTag().getList("custom_potion_effects", Tag.TAG_COMPOUND).stream()
                .map(CompoundTag.class::cast)
                .map(this::createPotionEffectEntry)
                .forEach(getEntries()::add);
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public EntryModel createNewListEntry() {
        return createPotionEffectEntry(null);
    }

    @Override
    public int getEntryHeight() {
        return 50;
    }

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.EFFECT;
    }

    private int getCustomPotionColor() {
        return getTag().contains("CustomPotionColor", Tag.TAG_INT) ? getTag().getInt("CustomPotionColor") : Color.NONE;
    }

    private void setCustomPotionColor(int color) {
        if (color != Color.NONE) {
            getOrCreateTag().putInt("CustomPotionColor", color);
        } else {
            getOrCreateTag().remove("CustomPotionColor");
        }
    }

    private EntryModel createPotionEffectEntry(CompoundTag tag) {
        if (tag != null) {
            String id = tag.getString("id");
            int amplifier = tag.getInt("amplifier"); // defaults to 0
            int duration = tag.contains("duration", Tag.TAG_INT) ? tag.getInt("duration") : 1;
            boolean ambient = tag.getBoolean("ambient"); // defaults to false
            boolean showParticles = !tag.contains("show_particles", Tag.TAG_BYTE) || tag.getBoolean("show_particles");
            boolean showIcon = tag.getBoolean("show_icon");
            return new PotionEffectEntryModel(this, id, amplifier, duration, ambient, showParticles, showIcon, this::addPotionEffect);
        }
        return new PotionEffectEntryModel(this, BuiltInRegistries.MOB_EFFECT.getKey(MobEffects.MOVEMENT_SPEED).toString(), 0, 1, false, true, true, this::addPotionEffect);
    }

    @Override
    public void apply() {
        potionEffectList = new ListTag();
        super.apply();
        if (!potionEffectList.isEmpty()) {
            getOrCreateTag().put("custom_potion_effects", potionEffectList);
        } else if (getOrCreateTag().contains("custom_potion_effects")) {
            getOrCreateTag().remove("custom_potion_effects");
        }
    }

    private void addPotionEffect(String id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon) {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", id);
        tag.putInt("amplifier", amplifier);
        tag.putInt("duration", duration);
        tag.putBoolean("ambient", ambient);
        tag.putBoolean("show_particles", showParticles);
        tag.putBoolean("show_icon", showIcon);
        potionEffectList.add(tag);
    }
}
