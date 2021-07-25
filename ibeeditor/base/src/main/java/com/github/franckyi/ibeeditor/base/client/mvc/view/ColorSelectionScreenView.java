package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ColorSelectionScreenView extends EditorView {
    private TextField redField, greenField, blueField, hexField;

    @Override
    protected Node createHeader() {
        return hBox(header -> {
            header.add(hBox().prefWidth(16));
            header.add(label(translated("ibeeditor.gui.custom_color").aqua().bold(), true).textAlign(CENTER).prefHeight(20), 1);
            header.add(createButton("ibeeditor:textures/gui/settings.png", "ibeeditor.gui.settings").action(ModScreenHandler::openSettingsScreen));
            header.align(CENTER);
        });
    }

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(vBox(), 1);
            editor.add(vBox(center -> {
                center.add(vBox(rgb -> {
                    rgb.add(hBox(labels -> {
                        labels.add(label(translated("ibeeditor.gui.red")), 1);
                        labels.add(label(translated("ibeeditor.gui.green")), 1);
                        labels.add(label(translated("ibeeditor.gui.blue")), 1);
                        labels.spacing(4);
                    }));
                    rgb.add(hBox(fields -> {
                        fields.add(redField = textField().prefHeight(16).validator(Predicates.range(0, 256)), 1);
                        fields.add(greenField = textField().prefHeight(16).validator(Predicates.range(0, 256)), 1);
                        fields.add(blueField = textField().prefHeight(16).validator(Predicates.range(0, 256)), 1);
                        fields.spacing(4);
                    }));
                    rgb.fillWidth().spacing(4);
                }));
                center.add(vBox(hex -> {
                    hex.add(hBox(labels -> {
                        labels.add(label(text("Hex")), 1);
                        labels.add(hBox(), 1);
                        labels.add(hBox(), 1);
                        labels.spacing(4);
                    }));
                    hex.add(hBox(fields -> {
                        fields.add(hexField = textField().prefHeight(16).validator(Predicates.HEX_COLOR), 1);
                        fields.add(hBox(), 1);
                        fields.add(hBox(), 1);
                        fields.spacing(4);
                    }));
                    hex.fillWidth().spacing(4);
                }));
                center.fillWidth().spacing(10).align(CENTER);
            }), 4);
            editor.add(vBox(), 1);
            editor.fillHeight();
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

    public TextField getHexField() {
        return hexField;
    }
}
