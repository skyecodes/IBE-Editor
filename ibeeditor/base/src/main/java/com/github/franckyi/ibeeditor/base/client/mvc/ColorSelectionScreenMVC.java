package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ColorSelectionScreenController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ColorSelectionScreenView;

public final class ColorSelectionScreenMVC extends SimpleMVC<ColorSelectionScreenModel, ColorSelectionScreenView, ColorSelectionScreenController> {
    public static final ColorSelectionScreenMVC INSTANCE = new ColorSelectionScreenMVC();

    private ColorSelectionScreenMVC() {
        super(ColorSelectionScreenView::new, ColorSelectionScreenController::new);
    }
}
