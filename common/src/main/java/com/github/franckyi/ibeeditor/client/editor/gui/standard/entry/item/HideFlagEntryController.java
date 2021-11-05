package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.item.ItemHideFlagsCategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.BooleanEntryController;
import com.github.franckyi.ibeeditor.common.ModTexts;

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
