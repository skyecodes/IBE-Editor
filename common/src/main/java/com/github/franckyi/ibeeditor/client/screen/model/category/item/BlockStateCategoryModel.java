package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.apache.commons.lang3.StringUtils;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class BlockStateCategoryModel extends BlockCategoryModel {
    public BlockStateCategoryModel(BlockEditorModel editor) {
        super(ModTexts.BLOCK_STATE, editor);
    }

    @Override
    protected void setupEntries() {
        for (Property<?> property : getBaseState().getProperties()) {
            MutableComponent name = text(StringUtils.capitalize(property.getName().toLowerCase()));
            if (property instanceof BooleanProperty booleanProperty) {
                getEntries().add(new BooleanEntryModel(this, name,
                        getBaseState().getValue(booleanProperty), value -> updateState(booleanProperty, value)));
            } else if (property instanceof IntegerProperty integerProperty) {
                getEntries().add(new IntegerEntryModel(this, name,
                        getBaseState().getValue(integerProperty), value -> updateState(integerProperty, value),
                        integerProperty.getPossibleValues()::contains));
            } else {
                addEnumProperty(name, property);
            }
        }
    }

    private <E extends Comparable<E>> void addEnumProperty(MutableComponent text, Property<E> property) {
        getEntries().add(new EnumEntryModel<>(this, text, property.getPossibleValues(),
                getBaseState().getValue(property), value -> updateState(property, value)));
    }
}
