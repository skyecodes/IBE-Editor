package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ArmorColorSelectionScreenController;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ColorSelectionScreenController;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.PotionColorSelectionScreenController;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.TextColorSelectionScreenController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ArmorColorSelectionScreenView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ColorSelectionScreenView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.PotionColorSelectionScreenView;
import com.github.franckyi.ibeeditor.base.client.mvc.view.TextColorSelectionScreenView;

public final class ColorSelectionScreenMVC implements MVC<ColorSelectionScreenModel, ColorSelectionScreenView, ColorSelectionScreenController<ColorSelectionScreenView>> {
    public static final ColorSelectionScreenMVC INSTANCE = new ColorSelectionScreenMVC();

    private ColorSelectionScreenMVC() {
    }

    @Override
    public ColorSelectionScreenView setup(ColorSelectionScreenModel model) {
        switch (model.getTarget()) {
            case TEXT:
                return MVC.createViewAndBind(model, TextColorSelectionScreenView::new, TextColorSelectionScreenController::new);
            case POTION:
                return MVC.createViewAndBind(model, PotionColorSelectionScreenView::new, PotionColorSelectionScreenController::new);
            case LEATHER_ARMOR:
                return MVC.createViewAndBind(model, ArmorColorSelectionScreenView::new, ArmorColorSelectionScreenController::new);
        }
        return null;
    }
}
