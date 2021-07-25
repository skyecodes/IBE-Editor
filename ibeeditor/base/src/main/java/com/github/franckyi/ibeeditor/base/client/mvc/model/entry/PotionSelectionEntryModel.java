package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.ClientCache;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;

import java.util.List;
import java.util.function.Consumer;

public class PotionSelectionEntryModel extends SelectionEntryModel {
    private final IntegerProperty customColorProperty;
    private final Consumer<Integer> otherAction;

    public PotionSelectionEntryModel(CategoryModel category, Text label, String potionName, int customColor, Consumer<String> action, Consumer<Integer> otherAction) {
        super(category, label, potionName, action);
        customColorProperty = DataBindings.getPropertyFactory().createIntegerProperty(customColor);
        this.otherAction = otherAction;
    }

    @Override
    public void apply() {
        super.apply();
        otherAction.accept(getCustomColor());
    }

    public int getCustomColor() {
        return customColorProperty().getValue();
    }

    private IntegerProperty customColorProperty() {
        return customColorProperty;
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
        return null;
    }

    @Override
    public List<? extends ListSelectionItemModel> getSelectionItems() {
        return null;
    }
}
