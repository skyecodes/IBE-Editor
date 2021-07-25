package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ColorSelectionScreenView;

public class ColorSelectionScreenController extends AbstractController<ColorSelectionScreenModel, ColorSelectionScreenView> {
    public ColorSelectionScreenController(ColorSelectionScreenModel model, ColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        softBind(getView().getHexField().textProperty(), model.valueProperty());
        getView().getRedField().textProperty().addListener(() -> updateColor(true));
        getView().getGreenField().textProperty().addListener(() -> updateColor(true));
        getView().getBlueField().textProperty().addListener(() -> updateColor(true));
        getView().getHexField().textProperty().addListener(() -> updateColor(false));
        getView().getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
        getView().getDoneButton().onAction(() -> {
            getModel().apply();
            Guapi.getScreenHandler().hideScene();
        });
        getView().getDoneButton().disableProperty().bind(getView().getRedField().validProperty()
                .and(getView().getBlueField().validProperty())
                .and(getView().getRedField().validProperty())
                .and(getView().getHexField().validProperty()).not());
    }

    private void updateColor(boolean rgbChanged) {
        if (rgbChanged && getView().getRedField().isValid() && getView().getGreenField().isValid() && getView().getBlueField().isValid()) {
            int red = Integer.parseInt(getView().getRedField().getText());
            int green = Integer.parseInt(getView().getGreenField().getText());
            int blue = Integer.parseInt(getView().getBlueField().getText());
            getView().getHexField().setText(String.format("#%02x%02x%02x", red, green, blue));
        } else if (getView().getHexField().isValid()) {
            String hex = getView().getHexField().getText();
            if (hex.startsWith("#")) {
                hex = hex.substring(1);
            }
            getView().getRedField().setText(Integer.valueOf(hex.substring(0, 2), 16).toString());
            getView().getGreenField().setText(Integer.valueOf(hex.substring(2, 4), 16).toString());
            getView().getBlueField().setText(Integer.valueOf(hex.substring(4, 6), 16).toString());
        }
    }
}
