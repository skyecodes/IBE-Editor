package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.SelectionEntryModel;
import com.github.franckyi.ibeeditor.client.util.selection.ListSelectionItemCache;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.function.Consumer;

public class PotionSelectionEntryModel extends SelectionEntryModel {
    private int defaultCustomColor;
    private final IntegerProperty customColorProperty;
    private final Consumer<Integer> customColorAction;

    public PotionSelectionEntryModel(CategoryModel category, MutableComponent label, String potionName, int customColor, Consumer<String> action, Consumer<Integer> customColorAction) {
        super(category, label, potionName, action);
        defaultCustomColor = customColor;
        customColorProperty = IntegerProperty.create(customColor);
        this.customColorAction = customColorAction;
    }

    @Override
    public void apply() {
        super.apply();
        customColorAction.accept(getCustomColor());
        defaultCustomColor = getCustomColor();
    }

    public int getCustomColor() {
        return customColorProperty().getValue();
    }

    public IntegerProperty customColorProperty() {
        return customColorProperty;
    }

    public void setCustomColor(int value) {
        customColorProperty().setValue(value);
    }

    public void resetCustomColor() {
        setCustomColor(defaultCustomColor);
    }

    @Override
    public Type getType() {
        return Type.SELECTION_POTION;
    }

    @Override
    public List<String> getSuggestions() {
        return ListSelectionItemCache.getPotionSuggestions();
    }

    @Override
    public MutableComponent getSuggestionScreenTitle() {
        return ModTexts.POTION;
    }

    @Override
    public List<? extends ListSelectionItemModel> getSelectionItems() {
        return ListSelectionItemCache.getPotionSelectionItems();
    }
}
