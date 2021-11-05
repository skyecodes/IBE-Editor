package com.github.franckyi.ibeeditor.client.editor.gui.standard.category.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.ItemCategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.PotionEffectEntryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item.PotionSelectionEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.TagHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.MutableComponent;

import java.util.stream.Collectors;

public class ItemPotionEffectsCategoryModel extends ItemCategoryModel {
    private ListTag potionEffectList;

    public ItemPotionEffectsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.POTION_EFFECTS, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new PotionSelectionEntryModel(this, ModTexts.DEFAULT_POTION,
                getBaseTag().getString("Potion"), getCustomPotionColor(),
                p -> getNewTag().putString("Potion", p), this::setCustomPotionColor));
        getEntries().addAll(getBaseTag().getList("CustomPotionEffects", TagHelper.COMPOUND_ID)
                .stream()
                .map(CompoundTag.class::cast)
                .map(this::createPotionEffectEntry)
                .collect(Collectors.toList()));
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

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.EFFECT;
    }

    private int getCustomPotionColor() {
        return getBaseTag().contains("CustomPotionColor", TagHelper.INT_ID) ? getBaseTag().getInt("CustomPotionColor") : Color.NONE;
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
            int amplifier = tag.getInt("Amplifier"); // defaults to 0
            int duration = tag.contains("Duration", TagHelper.INT_ID) ? tag.getInt("Duration") : 1;
            boolean ambient = tag.getBoolean("Ambient"); // defaults to false
            boolean showParticles = !tag.contains("ShowParticles", TagHelper.BYTE_ID) || tag.getBoolean("ShowParticles");
            boolean showIcon = tag.getBoolean("ShowIcon");
            return new PotionEffectEntryModel(this, id, amplifier, duration, ambient, showParticles, showIcon, this::addPotionEffect);
        }
        return new PotionEffectEntryModel(this, 1, 0, 1, false, true, true, this::addPotionEffect);
    }

    @Override
    public void apply(CompoundTag nbt) {
        potionEffectList = new ListTag();
        super.apply(nbt);
        if (!potionEffectList.isEmpty()) {
            getNewTag().put("CustomPotionEffects", potionEffectList);
        } else {
            getNewTag().remove("CustomPotionEffects");
        }
    }

    private void addPotionEffect(int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Id", id);
        tag.putInt("Amplifier", amplifier);
        tag.putInt("Duration", duration);
        tag.putBoolean("Ambient", ambient);
        tag.putBoolean("ShowParticles", showParticles);
        tag.putBoolean("ShowIcon", showIcon);
        potionEffectList.add(tag);
    }
}
