package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemHideFlagsCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.HideFlagEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.HideFlagEntryView;

import java.util.stream.IntStream;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class HideFlagEntryController extends BooleanEntryController<HideFlagEntryModel, HideFlagEntryView> {
    public HideFlagEntryController(HideFlagEntryModel model, HideFlagEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        if (model.getHideFlag() == ItemHideFlagsCategoryModel.HideFlag.OTHER) {
            IntStream.range(0, 8).forEachOrdered(i -> view.getLabel().getTooltip().add(
                    translated("ibeeditor.gui.hide_other_tooltip_" + i)));
        }
    }
}
