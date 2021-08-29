package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.PotionEffectEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.PotionSelectionEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.stream.Collectors;

public class ItemPotionEffectsCategoryModel extends ItemCategoryModel {
    private IListTag potionEffectList;

    public ItemPotionEffectsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.POTION_EFFECTS, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new PotionSelectionEntryModel(this, ModTexts.DEFAULT_POTION,
                getBaseTag().getString("Potion"), getCustomPotionColor(),
                p -> getNewTag().putString("Potion", p), this::setCustomPotionColor));
        getEntries().addAll(getBaseTag().getList("CustomPotionEffects", ITag.COMPOUND_ID)
                .stream()
                .map(ICompoundTag.class::cast)
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
    protected IText getAddListEntryButtonTooltip() {
        return ModTexts.EFFECT;
    }

    private int getCustomPotionColor() {
        return getBaseTag().contains("CustomPotionColor", ITag.INT_ID) ? getBaseTag().getInt("CustomPotionColor") : Color.NONE;
    }

    private void setCustomPotionColor(int color) {
        if (color != Color.NONE) {
            getNewTag().putInt("CustomPotionColor", color);
        } else {
            getNewTag().remove("CustomPotionColor");
        }
    }

    private EntryModel createPotionEffectEntry(ICompoundTag tag) {
        if (tag != null) {
            int id = tag.getInt("Id");
            int amplifier = tag.getInt("Amplifier"); // defaults to 0
            int duration = tag.contains("Duration", ITag.INT_ID) ? tag.getInt("Duration") : 1;
            boolean ambient = tag.getBoolean("Ambient"); // defaults to false
            boolean showParticles = !tag.contains("ShowParticles", ITag.BYTE_ID) || tag.getBoolean("ShowParticles");
            boolean showIcon = tag.getBoolean("ShowIcon");
            return new PotionEffectEntryModel(this, id, amplifier, duration, ambient, showParticles, showIcon, this::addPotionEffect);
        }
        return new PotionEffectEntryModel(this, 1, 0, 1, false, true, true, this::addPotionEffect);
    }

    @Override
    public void apply(ICompoundTag nbt) {
        potionEffectList = IListTag.create();
        super.apply(nbt);
        if (!potionEffectList.isEmpty()) {
            getNewTag().putTag("CustomPotionEffects", potionEffectList);
        } else {
            getNewTag().remove("CustomPotionEffects");
        }
    }

    private void addPotionEffect(int id, int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon) {
        ICompoundTag tag = ICompoundTag.create();
        tag.putInt("Id", id);
        tag.putInt("Amplifier", amplifier);
        tag.putInt("Duration", duration);
        tag.putBoolean("Ambient", ambient);
        tag.putBoolean("ShowParticles", showParticles);
        tag.putBoolean("ShowIcon", showIcon);
        potionEffectList.add(tag);
    }
}
