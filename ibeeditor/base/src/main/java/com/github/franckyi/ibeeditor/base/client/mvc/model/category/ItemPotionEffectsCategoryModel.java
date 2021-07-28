package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.PotionEffectEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.PotionSelectionEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemPotionEffectsCategoryModel extends ItemCategoryModel {
    public ItemPotionEffectsCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.potion_effects", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new PotionSelectionEntryModel(this, translated("ibeeditor.gui.default_potion"),
                getBaseTag().getString("Potion"), getCustomPotionColor(),
                p -> getNewTag().putString("Potion", p), this::setCustomPotionColor));
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public EntryModel createListEntry() {
        return createPotionEffectEntry(null);
    }

    @Override
    public int getEntryHeight() {
        return 50;
    }

    private int getCustomPotionColor() {
        return getBaseTag().contains("CustomPotionColor", Tag.INT_ID) ? getBaseTag().getInt("CustomPotionColor") : Color.NONE;
    }

    private void setCustomPotionColor(int color) {
        if (color != Color.NONE) {
            getNewTag().putInt("CustomPotionColor", color);
        } else {
            getNewTag().remove("CustomPotionColor");
        }
    }

    private EntryModel createPotionEffectEntry(CompoundTag tag) {
        if (tag != null) {
            int id = tag.getInt("Id");
            int amplifier = tag.getInt("Amplifier");
            int duration = tag.getInt("Duration");
            boolean ambient = tag.getBoolean("Ambient");
            boolean showParticles = tag.getBoolean("ShowParticles");
            boolean showIcon = tag.getBoolean("ShowIcon");
            return new PotionEffectEntryModel(this, id, amplifier, duration, ambient, showParticles, showIcon, this::addPotionEffect);
        }
        return new PotionEffectEntryModel(this, 0, 0, 0, false, true, true, this::addPotionEffect);
    }

    private void addPotionEffect(int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon) {

    }
}
