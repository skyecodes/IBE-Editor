package com.github.franckyi.ibeeditor.client.screen.controller.selection.color;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.ColorSelectionScreenView;

public abstract class ColorSelectionScreenController<V extends ColorSelectionScreenView> extends AbstractController<ColorSelectionScreenModel, V> {
    protected ColorSelectionScreenController(ColorSelectionScreenModel model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getRedSlider().valueProperty().bindBidirectional(model.redValueProperty());
        view.getGreenSlider().valueProperty().bindBidirectional(model.greenValueProperty());
        view.getBlueSlider().valueProperty().bindBidirectional(model.blueValueProperty());
        updateHexFromRGB();
        view.getHexField().textProperty().bindBidirectional(model.hexValueProperty());
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
