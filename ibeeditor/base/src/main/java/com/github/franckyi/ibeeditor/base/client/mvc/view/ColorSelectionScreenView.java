package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Slider;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class ColorSelectionScreenView extends EditorView {
    private Slider redSlider, greenSlider, blueSlider;
    private TextField hexField;
    private HBox exampleBox;

    @Override
    protected Node createHeader() {
        return hBox(header -> {
            header.add(hBox().prefWidth(16));
            header.add(label(translated("ibeeditor.gui.choose_custom_color").aqua().bold(), true).textAlign(CENTER).prefHeight(20), 1);
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
                        fields.add(redSlider = colorSlider(), 1);
                        fields.add(greenSlider = colorSlider(), 1);
                        fields.add(blueSlider = colorSlider(), 1);
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
                        fields.add(hexField = textField().prefHeight(16), 1);
                        fields.add(createExample(), 1);
                        fields.add(exampleBox = hBox().prefHeight(16), 1);
                        fields.spacing(4).align(CENTER);
                    }));
                    hex.fillWidth().spacing(4);
                }));
                center.fillWidth().spacing(10).align(CENTER);
            }), 4);
            editor.add(vBox(), 1);
            editor.fillHeight();
        });
    }

    private Slider colorSlider() {
        return slider(0, 0, 255, 1);
    }

    protected abstract Node createExample();

    public Slider getRedSlider() {
        return redSlider;
    }

    public Slider getGreenSlider() {
        return greenSlider;
    }

    public Slider getBlueSlider() {
        return blueSlider;
    }

    public TextField getHexField() {
        return hexField;
    }

    public HBox getExampleBox() {
        return exampleBox;
    }
}
