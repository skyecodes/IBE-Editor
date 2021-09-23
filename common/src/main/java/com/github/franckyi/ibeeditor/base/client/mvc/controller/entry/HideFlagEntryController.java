package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemHideFlagsCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.HideFlagEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.HideFlagEntryView;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public class HideFlagEntryController extends BooleanEntryController<HideFlagEntryModel, HideFlagEntryView> {
    public HideFlagEntryController(HideFlagEntryModel model, HideFlagEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        if (model.getHideFlag() == ItemHideFlagsCategoryModel.HideFlag.OTHER) {
            view.getLabel().getTooltip().addAll(ModTexts.HIDE_OTHER_TOOLTIP);
        }
    }
}
