package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ColorSelectionScreenView;

public abstract class ColorSelectionScreenController<V extends ColorSelectionScreenView> extends AbstractController<ColorSelectionScreenModel, V> {
    protected ColorSelectionScreenController(ColorSelectionScreenModel model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getRedSlider().setValue(model.getRedValue());
        view.getGreenSlider().setValue(model.getGreenValue());
        view.getBlueSlider().setValue(model.getBlueValue());
        softBind(view.getHexField().textProperty(), model.hexValueProperty());
        softBind(view.getRedSlider().valueProperty(), model.redValueProperty());
        softBind(view.getGreenSlider().valueProperty(), model.greenValueProperty());
        softBind(view.getBlueSlider().valueProperty(), model.blueValueProperty());
        model.redValueProperty().addListener(() -> updateColor(true));
        model.greenValueProperty().addListener(() -> updateColor(true));
        model.blueValueProperty().addListener(() -> updateColor(true));
        model.hexValueProperty().addListener(() -> updateColor(false));
        view.getHexField().setValidator(this::validateHexString);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
        view.getDoneButton().onAction(() -> {
            getModel().apply();
            Guapi.getScreenHandler().hideScene();
        });
        updateHexFromRGB();
    }

    private void updateColor(boolean rgbChanged) {
        view.getDoneButton().setDisable(!view.getHexField().isValid());
        if (rgbChanged) {
            updateHexFromRGB();
        } else if (view.getHexField().isValid()) {
            updateRGBFromHex();
        }
    }

    private void updateHexFromRGB() {
        int red = (int) (view.getRedSlider().getValue());
        int green = (int) (view.getGreenSlider().getValue());
        int blue = (int) (view.getBlueSlider().getValue());
        model.setHexValue(String.format("#%02x%02x%02x", red, green, blue));
        updateExample();
    }

    private void updateRGBFromHex() {
        String hex = view.getHexField().getText().substring(1);
        model.setRedValue(Integer.valueOf(hex.substring(0, 2), 16));
        model.setGreenValue(Integer.valueOf(hex.substring(2, 4), 16));
        model.setBlueValue(Integer.valueOf(hex.substring(4, 6), 16));
        updateExample();
    }

    protected void updateExample() {
        view.getExampleBox().setBackgroundColor(Color.fromRGB(
                (int) model.getRedValue(),
                (int) model.getGreenValue(),
                (int) model.getBlueValue()
        ));
    }

    private boolean validateHexString(String s) {
        try {
            if (s.length() != 7 || !s.startsWith("#")) {
                return false;
            }
            Integer.parseInt(s.substring(1), 16);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
