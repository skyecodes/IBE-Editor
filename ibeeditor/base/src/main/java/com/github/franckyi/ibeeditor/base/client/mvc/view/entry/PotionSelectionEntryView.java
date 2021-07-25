package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.util.Predicates;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class PotionSelectionEntryView extends SelectionEntryView {
    private TextField redField, greenField, blueField;

    @Override
    protected Node createLabeledContent() {
        return vBox(root -> {
            root.add(super.createLabeledContent());
            root.add(hBox(color -> {
                color.add(label(translated("ibeeditor.gui.potion_color")), 1);
                color.add(redField = textField().validator(Predicates.range(0, 256)), 1);
                color.add(greenField = textField().validator(Predicates.range(0, 256)), 1);
                color.add(blueField = textField().validator(Predicates.range(0, 256)), 1);
            }));
            root.fillWidth().spacing(2).align(CENTER);
        });
    }

    public TextField getRedField() {
        return redField;
    }

    public TextField getGreenField() {
        return greenField;
    }

    public TextField getBlueField() {
        return blueField;
    }
}
