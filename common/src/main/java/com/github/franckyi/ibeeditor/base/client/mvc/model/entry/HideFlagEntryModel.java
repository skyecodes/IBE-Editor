package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemHideFlagsCategoryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.text;

public class HideFlagEntryModel extends BooleanEntryModel {
    private final ItemHideFlagsCategoryModel.HideFlag hideFlag;

    public HideFlagEntryModel(CategoryModel category, ItemHideFlagsCategoryModel.HideFlag hideFlag, boolean value, Consumer<Boolean> action) {
        super(category, getHideFlagLabel(hideFlag), value, action);
        this.hideFlag = hideFlag;
        withWeight(2);
    }

    private static MutableComponent getHideFlagLabel(ItemHideFlagsCategoryModel.HideFlag hideFlag) {
        MutableComponent label = ModTexts.hide(hideFlag.getName());
        if (hideFlag == ItemHideFlagsCategoryModel.HideFlag.OTHER) {
            label.append(text("*"));
        }
        return label;
    }

    public ItemHideFlagsCategoryModel.HideFlag getHideFlag() {
        return hideFlag;
    }

    @Override
    public Type getType() {
        return Type.HIDE_FLAG;
    }
}
