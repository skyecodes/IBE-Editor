package com.github.franckyi.ibeeditor.client.util.selection.gui.color;

import com.github.franckyi.guapi.api.mvc.MVC;

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
