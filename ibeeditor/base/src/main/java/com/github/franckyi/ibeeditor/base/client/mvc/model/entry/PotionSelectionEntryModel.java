package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.ClientCache;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;

import java.util.List;
import java.util.function.Consumer;

public class PotionSelectionEntryModel extends SelectionEntryModel {
    private int defaultCustomColor;
    private final IntegerProperty customColorProperty;
    private final Consumer<Integer> customColorAction;

    public PotionSelectionEntryModel(CategoryModel category, Text label, String potionName, int customColor, Consumer<String> action, Consumer<Integer> customColorAction) {
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
        return ClientCache.getPotionSuggestions();
    }

    @Override
    public String getSuggestionScreenTitle() {
        return "ibeeditor.gui.potion";
    }

    @Override
    public List<? extends ListSelectionItemModel> getSelectionItems() {
        return ClientCache.getPotionSelectionItems();
    }
}
