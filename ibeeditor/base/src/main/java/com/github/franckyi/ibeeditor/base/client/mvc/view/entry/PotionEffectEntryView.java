package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.util.Predicates;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class PotionEffectEntryView extends SelectionEntryView {
    private TextField amplifierField;
    private TextField durationField;
    private CheckBox ambientBox;
    private CheckBox showParticlesBox;
    private CheckBox showIconBox;

    @Override
    public void build() {
        super.build();
        getTextField().setPlaceholder(translated("ibeeditor.gui.effect"));
    }

    @Override
    protected Node createLabeledContent() {
        return vBox(root -> {
            root.add(super.createLabeledContent());
            root.add(hBox(bottom -> {
                bottom.add(amplifierField = textField().prefHeight(16).validator(Predicates.range(0, Integer.MAX_VALUE))
                        .tooltip(translated("ibeeditor.gui.amplifier")), 1);
                bottom.add(durationField = textField().prefHeight(16).validator(Predicates.range(1, Integer.MAX_VALUE))
                        .tooltip(translated("ibeeditor.gui.duration")), 1);
                bottom.add(hBox(boxes -> {
                    boxes.add(ambientBox = checkBox().tooltip(translated("ibeeditor.gui.ambient")));
                    boxes.add(showParticlesBox = checkBox().tooltip(translated("ibeeditor.gui.show_particles")));
                    boxes.add(showIconBox = checkBox().tooltip(translated("ibeeditor.gui.show_icon")));
                    boxes.spacing(5);
                }));
                bottom.spacing(5);
            }));
            root.spacing(5).fillWidth();
        });
    }

    public TextField getAmplifierField() {
        return amplifierField;
    }

    public TextField getDurationField() {
        return durationField;
    }

    public CheckBox getAmbientBox() {
        return ambientBox;
    }

    public CheckBox getShowParticlesBox() {
        return showParticlesBox;
    }

    public CheckBox getShowIconBox() {
        return showIconBox;
    }
}
