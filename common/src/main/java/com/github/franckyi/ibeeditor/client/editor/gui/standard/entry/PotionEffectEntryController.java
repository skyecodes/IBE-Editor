package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

public class PotionEffectEntryController extends SelectionEntryController<PotionEffectEntryModel, PotionEffectEntryView> {
    public PotionEffectEntryController(PotionEffectEntryModel model, PotionEffectEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        updateAmplifierView();
        updateDurationView();
        view.getAmbientBox().setChecked(model.isAmbient());
        view.getShowParticlesBox().setChecked(model.isShowParticles());
        view.getShowIconBox().setChecked(model.isShowIcon());
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
        softBind(model.ambientProperty(), view.getAmbientBox().checkedProperty());
        softBind(model.showParticlesProperty(), view.getShowParticlesBox().checkedProperty());
        softBind(model.showIconProperty(), view.getShowIconBox().checkedProperty());
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
