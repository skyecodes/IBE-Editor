package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.PotionSelectionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.PotionSelectionEntryView;

public class PotionSelectionEntryController extends SelectionEntryController<PotionSelectionEntryModel, PotionSelectionEntryView> {
    public PotionSelectionEntryController(PotionSelectionEntryModel model, PotionSelectionEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
    }
}
