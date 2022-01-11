package com.github.franckyi.ibeeditor.client.screen.controller.entry.item;

import com.github.franckyi.ibeeditor.client.screen.controller.entry.SelectionEntryController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.PotionEffectEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.item.PotionEffectEntryView;

public class PotionEffectEntryController extends SelectionEntryController<PotionEffectEntryModel, PotionEffectEntryView> {
    public PotionEffectEntryController(PotionEffectEntryModel model, PotionEffectEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        updateAmplifierView();
        updateDurationView();
        view.getAmbientBox().checkedProperty().bindBidirectional(model.ambientProperty());
        view.getShowParticlesBox().checkedProperty().bindBidirectional(model.showParticlesProperty());
        view.getShowIconBox().checkedProperty().bindBidirectional(model.showIconProperty());
        view.getAmplifierField().textProperty().addListener(value -> {
            if (view.getAmplifierField().isValid()) {
                model.setAmplifier(Integer.parseInt(value));
            }
        });
        model.amplifierProperty().addListener(this::updateAmplifierView);
        view.getDurationField().textProperty().addListener(value -> {
            if (view.getDurationField().isValid()) {
                model.setDuration(Integer.parseInt(value));
            }
        });
        model.durationProperty().addListener(this::updateDurationView);
        view.getAmplifierField().validProperty().addListener(this::updateValidity);
        view.getDurationField().validProperty().addListener(this::updateValidity);
    }

    private void updateValidity() {
        model.setValid(view.getAmplifierField().isValid() && view.getDurationField().isValid());
    }

    private void updateAmplifierView() {
        view.getAmplifierField().setText(Integer.toString(model.getAmplifier()));
    }

    private void updateDurationView() {
        view.getDurationField().setText(Integer.toString(model.getDuration()));
    }
}
