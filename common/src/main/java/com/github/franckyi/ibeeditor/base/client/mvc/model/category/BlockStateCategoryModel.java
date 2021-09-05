package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.github.franckyi.gameadapter.api.common.world.IProperty;
import com.github.franckyi.ibeeditor.base.client.mvc.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import org.apache.commons.lang3.StringUtils;

import static com.github.franckyi.guapi.api.GuapiHelper.text;

public class BlockStateCategoryModel extends BlockCategoryModel {
    public BlockStateCategoryModel(BlockEditorModel editor) {
        super(ModTexts.BLOCK_STATE, editor);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void setupEntries() {
        for (IProperty<?> property : getBaseState().getProperties()) {
            IPlainText name = text(StringUtils.capitalize(property.getName().toLowerCase()));
            if (property.isBooleanProperty()) {
                IProperty<Boolean> booleanProperty = (IProperty<Boolean>) property;
                getEntries().add(new BooleanEntryModel(this, name,
                        getBaseState().getValue(booleanProperty), value -> updateState(booleanProperty, value)));
            } else if (property.isIntegerProperty()) {
                IProperty<Integer> integerProperty = (IProperty<Integer>) property;
                getEntries().add(new IntegerEntryModel(this, name,
                        getBaseState().getValue(integerProperty), value -> updateState(integerProperty, value),
                        integerProperty.getAllowedValues()::contains));
            } else {
                addEnumProperty(name, property);
            }
        }
    }

    private <E extends Comparable<E>> void addEnumProperty(IPlainText text, IProperty<E> property) {
        getEntries().add(new EnumEntryModel<>(this, text, property.getAllowedValues(),
                getBaseState().getValue(property), value -> updateState(property, value)));
    }
}
