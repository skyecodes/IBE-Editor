package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemHideFlagsEditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ItemHideFlagEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.ItemHideFlagEditorEntryView;

import java.util.stream.IntStream;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemHideFlagEditorEntryController extends BooleanEditorEntryController<ItemHideFlagEditorEntryModel, ItemHideFlagEditorEntryView> {
    public ItemHideFlagEditorEntryController(ItemHideFlagEditorEntryModel model, ItemHideFlagEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        if (model.getHideFlag() == ItemHideFlagsEditorCategoryModel.HideFlag.OTHER) {
            IntStream.range(0, 8).forEachOrdered(i -> view.getLabel().getTooltip().add(
                    translated("ibeeditor.gui.editor.item.entry.hide_other_tooltip_" + i)));
        }
    }
}
