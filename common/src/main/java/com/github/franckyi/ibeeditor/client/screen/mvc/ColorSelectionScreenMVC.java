package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.client.screen.controller.selection.color.ArmorColorSelectionScreenController;
import com.github.franckyi.ibeeditor.client.screen.controller.selection.color.ColorSelectionScreenController;
import com.github.franckyi.ibeeditor.client.screen.controller.selection.color.PotionColorSelectionScreenController;
import com.github.franckyi.ibeeditor.client.screen.controller.selection.color.TextColorSelectionScreenController;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.ArmorColorSelectionScreenView;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.ColorSelectionScreenView;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.PotionColorSelectionScreenView;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.TextColorSelectionScreenView;

public final class ColorSelectionScreenMVC implements MVC<ColorSelectionScreenModel, ColorSelectionScreenView, ColorSelectionScreenController<ColorSelectionScreenView>> {
    public static final ColorSelectionScreenMVC INSTANCE = new ColorSelectionScreenMVC();

    private ColorSelectionScreenMVC() {
    }

    @Override
    public ColorSelectionScreenView setup(ColorSelectionScreenModel model) {
        return switch (model.getTarget()) {
            case TEXT -> MVC.createViewAndBind(model, TextColorSelectionScreenView::new, TextColorSelectionScreenController::new);
            case POTION -> MVC.createViewAndBind(model, PotionColorSelectionScreenView::new, PotionColorSelectionScreenController::new);
            case LEATHER_ARMOR -> MVC.createViewAndBind(model, ArmorColorSelectionScreenView::new, ArmorColorSelectionScreenController::new);
        };
    }
}
